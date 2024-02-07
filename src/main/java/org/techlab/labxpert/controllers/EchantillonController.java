package org.techlab.labxpert.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.techlab.labxpert.dtos.AnalyseDTO;
import org.techlab.labxpert.dtos.EchantillonDTO;
import org.techlab.labxpert.dtos.OutilDTO;
import org.techlab.labxpert.dtos.UtilisateurDTO;
import org.techlab.labxpert.entity.Echantillon;
import org.techlab.labxpert.entity.Outil;
import org.techlab.labxpert.entity.Utilisateur;
import org.techlab.labxpert.service.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/api/v1/echantillon", produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200")
public class EchantillonController {

    @Autowired
    I_Echantillon i_echantillon;
    @Autowired
    I_Analyse i_analyse;
    @Autowired
    I_Utilisateur i_utilisateur;
    @Autowired
    I_Outil_Echantillon i_outil_echantillon;
    @Autowired
    I_Outil i_outil;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<EchantillonDTO> showEchantillons() {
        // API pour Afficher liste Echantillon
        return i_echantillon.showEhantillon();
    }
    @GetMapping("/{id}")
    public ResponseEntity<EchantillonDTO> showEchantillonWithId(@PathVariable(value = "id") Long id) {
        // API pour Afficher Echantillon par id
        EchantillonDTO echantillonDTO = i_echantillon.showEchantillonwithid(id);
        return ResponseEntity.ok().body(echantillonDTO);
    }
    @PutMapping
    public ResponseEntity<EchantillonDTO> modEchantillon(@RequestBody @Valid EchantillonDTO echantillonDTO) {
        // API pour afficher Echantillon par Id
        EchantillonDTO modifiedEchantillon = i_echantillon.modEchantillon(echantillonDTO);
        return new ResponseEntity<>(modifiedEchantillon, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public Map<String,Boolean> delUser(@PathVariable(value = "id") Long id ){
        // API pour Supprimer Utilisateur
        EchantillonDTO echantillonDTO= i_echantillon.showEchantillonwithid( id);
        //echantillonDTO.setDeleted(true);
        Map<String,Boolean> response=new HashMap<>();
        if(i_echantillon.delEchantillhon(echantillonDTO)){
            response.put("deleted",Boolean.TRUE);
        }
        return response;
    }
    @PostMapping
    ResponseEntity<EchantillonDTO> addEchantillon( @RequestBody @Valid EchantillonDTO echantillondto){
        // API pour Ajouter un Echantillon
        Utilisateur utilisateur=modelMapper.map(i_utilisateur.showUserwithid(echantillondto.getUtilisateur().getId()),Utilisateur.class);
        echantillondto.setUtilisateur(utilisateur);
        EchantillonDTO echantillonDTO=i_echantillon.addEchantillon(echantillondto);
        AnalyseDTO analyseDTO=new AnalyseDTO();
        analyseDTO.setEchantillon(modelMapper.map(echantillonDTO, Echantillon.class));
        analyseDTO.setNomAnalyse(echantillonDTO.getTypeAnalyse());
        i_analyse.addAnalyse(analyseDTO);
        if(echantillondto.getOutilEchantillonList()!=null){
        echantillondto.getOutilEchantillonList().forEach(outilEchantillon -> {
            outilEchantillon.setOutil(modelMapper.map(i_outil.outilById(outilEchantillon.getOutil().getIdOutil()), Outil.class));
            outilEchantillon.setEchantillon(modelMapper.map(echantillonDTO, Echantillon.class));
            i_outil_echantillon.add(outilEchantillon);
            OutilDTO outilDTO=modelMapper.map(outilEchantillon.getOutil(),OutilDTO.class);
            if(outilEchantillon.getQuantite()<=outilDTO.getQuantite()){
            outilDTO.setQuantite(outilDTO.getQuantite()-outilEchantillon.getQuantite());
            i_outil.modOutil(outilDTO);
            }
        });
        }

        return new ResponseEntity<>(echantillonDTO, HttpStatus.CREATED);
    }

}
