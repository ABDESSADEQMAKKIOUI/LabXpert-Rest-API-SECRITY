package org.techlab.labxpert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.techlab.labxpert.dtos.FournisseurDTO;
import org.techlab.labxpert.service.I_Fournisseur;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value="/api/v1/fournisseur", produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200")
public class FournisseurController {

    @Autowired
    I_Fournisseur i_fournisseur;

    @GetMapping
    public ResponseEntity<List<FournisseurDTO>> showFournisseurs(){
        // API pour Afficher Liste Fournisseur
        List<FournisseurDTO> fournisseurDTOS=i_fournisseur.showFournisseurs();
        return new ResponseEntity<>(fournisseurDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FournisseurDTO> getFournisseur(@PathVariable(value = "id") Long id_fournisseur){
        // API pour Afficher fournisseur par  id
        FournisseurDTO fournisseurDTO=i_fournisseur.getFournisseur(id_fournisseur);
        return new ResponseEntity<>(fournisseurDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<FournisseurDTO> addFournisseur(@RequestBody @Valid FournisseurDTO fournisseurDTO){
        // API pour Ajouter Fournisseur
        FournisseurDTO fournisseur=i_fournisseur.addFournisseur(fournisseurDTO);
        return new ResponseEntity<>(fournisseur, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<FournisseurDTO> modFournisseur(@RequestBody @Valid FournisseurDTO fournisseurDTO){
        // API pour modifier Fournisseur
        FournisseurDTO fournisseur=i_fournisseur.modFournisseur(fournisseurDTO);
        return new ResponseEntity<>(fournisseur, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public HashMap<String,Boolean> delFournisseur(@PathVariable(value = "id") Long id_fournisseur){
        // API pour Supprimer Fournisseur
        FournisseurDTO fournisseurDTO=i_fournisseur.getFournisseur(id_fournisseur);
        HashMap<String,Boolean> response=new HashMap<>();
        if(i_fournisseur.delFournisseur(fournisseurDTO)){
            response.put("delete",Boolean.TRUE);
        }
        return response;
    }
}
