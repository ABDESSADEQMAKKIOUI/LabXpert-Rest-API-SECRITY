package org.techlab.labxpert.controllers;


import net.sf.jasperreports.engine.JRException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.techlab.labxpert.dtos.AnalyseDTO;
import org.techlab.labxpert.dtos.EchantillonDTO;
import org.techlab.labxpert.dtos.PlanificationDTO;
import org.techlab.labxpert.dtos.ReactifDTO;
import org.techlab.labxpert.entity.Analyse;
import org.techlab.labxpert.entity.Echantillon;
import org.techlab.labxpert.entity.Reactif;
import org.techlab.labxpert.service.*;
import org.techlab.labxpert.service.serviceImp.ResultRepport;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/api/v1/analyse", produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200")
public class AnalyseController {
    @Autowired
    I_Analyse i_analyse;
    @Autowired
    I_Echantillon i_echantillon;
    @Autowired
    I_Rapport i_rapport;
    @Autowired
    I_Reactif i_reactif;
    @Autowired
    I_ReactifAnalyse i_ReactifAnalyse;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<AnalyseDTO>> getanalyses() {
        // API pour Afficher tous les analyses
        List<AnalyseDTO> analyses = i_analyse.showAnalyses();
        return new ResponseEntity<>(analyses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalyseDTO> getanalyse(@PathVariable(value = "id") Long id) {
        // API pour affiche analyse par id analyse
        AnalyseDTO analyse = i_analyse.showAnalyseWithId(id);
        return new ResponseEntity<>(analyse, HttpStatus.OK);
    }

    @GetMapping("/echantillon/{id}")
    public ResponseEntity<AnalyseDTO> addAnalyse(@PathVariable(value = "id") Long id_echantillong) {
        // API pour ajouter analyse
        EchantillonDTO echantillonDTO = i_echantillon.showEchantillonwithid(id_echantillong);
        AnalyseDTO analyseDTO = new AnalyseDTO();
        analyseDTO.setEchantillon(modelMapper.map(echantillonDTO, Echantillon.class));
        analyseDTO.setNomAnalyse(echantillonDTO.getTypeAnalyse());
        analyseDTO = i_analyse.addAnalyse(analyseDTO);
        return new ResponseEntity<>(analyseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<Resource> getpdf(@PathVariable(value = "id") Long id_Analyse) throws JRException, FileNotFoundException, ParseException {
        // API pour imprimer resultat d'analyse
        byte[] reportContent = i_rapport.exportReport(id_Analyse);
        ByteArrayResource resource = new ByteArrayResource(reportContent);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("Resultat.pdf")
                                .build().toString())
                .body(resource);
    }

    @PutMapping
    public ResponseEntity<AnalyseDTO> updateAnalyse(@RequestBody @Valid AnalyseDTO analyseDTO) {
        // API pour Modifier analyse
        AnalyseDTO analyseDTO1 = i_analyse.showAnalyseWithId(analyseDTO.getIdAnalyse());
        analyseDTO1.setDateFin(analyseDTO.getDateFin());
        analyseDTO1.setDateDebut(analyseDTO.getDateDebut());
        analyseDTO1.setCommantaire(analyseDTO.getCommantaire());
        analyseDTO1 = i_analyse.modAnalyse(analyseDTO1);
        AnalyseDTO finalAnalyseDTO = analyseDTO1;
        if(analyseDTO.getDateFin()!=null){
        if(analyseDTO.getReactifAnalyseList()!=null){
        analyseDTO.getReactifAnalyseList().forEach(reactifAnalyse -> {
            Reactif reactif=modelMapper.map(i_reactif.showReactifwithid(reactifAnalyse.getReactif().getIdReactif()),Reactif.class);
            reactifAnalyse.setAnalyse(modelMapper.map(finalAnalyseDTO, Analyse.class));
            reactifAnalyse.setReactif(reactif);
            i_ReactifAnalyse.add(reactifAnalyse);
            reactif.setQuantite(reactif.getQuantite()-reactifAnalyse.getQuantite());
            i_reactif.modReactif(modelMapper.map(reactif, ReactifDTO.class));
        });
        }
        }
        return new ResponseEntity<>(analyseDTO1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAnalyse(@PathVariable(value = "id") Long id_analyse) {
        // API pour supprimer analyse
        AnalyseDTO analyseDTO = i_analyse.showAnalyseWithId(id_analyse);
        Map<String, Boolean> response = new HashMap<>();
        if (i_analyse.delAnalyse(analyseDTO)) {
            response.put("deleted", Boolean.TRUE);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/planification")
    public ResponseEntity<PlanificationDTO> affectAnalyse(@RequestBody @Valid PlanificationDTO planificationDTO) {
        // API pour affecter une analyse
        planificationDTO = i_analyse.planifierAnalyse(planificationDTO);
        return new ResponseEntity<>(planificationDTO, HttpStatus.OK);
    }
}
