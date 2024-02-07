package org.techlab.labxpert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.techlab.labxpert.dtos.NormeDTO;
import org.techlab.labxpert.service.I_Norme;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/norme", produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200")
public class NormeController {

    @Autowired
    I_Norme i_norme;

    @GetMapping
    public List<NormeDTO> showAllNormes(){
        // API pour Afficher Liste Normes
        return i_norme.showNormes();
    }
    @PostMapping
    public ResponseEntity<NormeDTO> addNorme(@RequestBody @Valid NormeDTO normeDTO){
        // API pour Ajouter Norme
        NormeDTO normeDTO1=i_norme.addNorme(normeDTO);
        return new ResponseEntity<>(normeDTO1, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public HashMap<String,Boolean> delNorme(@PathVariable(value = "id") Long id){
        // API pour Supprimer Norme
        NormeDTO normeDTO=i_norme.getNormeById(id);
        HashMap<String,Boolean> response=new HashMap<>();
        if(i_norme.delNorme(normeDTO)){
            response.put("deleted",Boolean.TRUE);
        }
        return response;
    }
}
