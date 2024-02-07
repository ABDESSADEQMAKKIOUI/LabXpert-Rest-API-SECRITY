package org.techlab.labxpert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.techlab.labxpert.dtos.UtilisateurDTO;
import org.techlab.labxpert.entity.Utilisateur;
import org.techlab.labxpert.service.I_Utilisateur;
import org.techlab.labxpert.service.serviceImp.UtilisateurServiceImp;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/api/v1/utilisateur", produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {
    @Autowired
    I_Utilisateur i_utilisateur;

    @GetMapping
    public List allUsers(){
        //API pour Afficher Tous les utilisateurs
        List<UtilisateurDTO> listusers=i_utilisateur.showUsers();
        return listusers;
    }
    @GetMapping("{id}")
    public UtilisateurDTO showUser(@PathVariable(value = "id") Long id_User){
        // API Pour afficher Utilisateur par id
        UtilisateurDTO user=i_utilisateur.showUserwithid(id_User);
        return user;
    }
    @PostMapping
    public ResponseEntity<UtilisateurDTO> addUser(@RequestBody @Valid UtilisateurDTO utilisateurDTO){
        // API  pour ajouter utilisateur
        UtilisateurDTO utilisateurDTO1=i_utilisateur.addUser(utilisateurDTO);
        return new ResponseEntity<>(utilisateurDTO1, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<UtilisateurDTO> modUser(@RequestBody @Valid UtilisateurDTO utilisateurDTO){
        // API pour Modifier Utilisateur
        UtilisateurDTO utilisateurDTO1=i_utilisateur.modUser(utilisateurDTO);
        return new ResponseEntity<>(utilisateurDTO1, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public Map<String,Boolean> delUser(@PathVariable(value = "id") Long id_utilisateur ){
        // API pour Supprimer utilisateur
        UtilisateurDTO utilisateurDTO=i_utilisateur.showUserwithid(id_utilisateur);
        Map<String,Boolean> response=new HashMap<>();
        if(i_utilisateur.delUser(utilisateurDTO)){
            response.put("deleted",Boolean.TRUE);
        }
        return response;
    }
    @GetMapping("/login")
    public UtilisateurDTO login(@RequestParam String username,@RequestParam String password){
        // API pour Authentification
        UtilisateurDTO user=i_utilisateur.authentification(username,password);
        return user;
    }
}
