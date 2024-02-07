package org.techlab.labxpert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.techlab.labxpert.dtos.PatientDTO;
import org.techlab.labxpert.dtos.UtilisateurDTO;
import org.techlab.labxpert.entity.Patient;
import org.techlab.labxpert.exception.CustomErrorException;
import org.techlab.labxpert.exception.CustomErrorResponse;
import org.techlab.labxpert.service.I_Patient;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/api/v1/patient", produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {
    @Autowired
    I_Patient i_patient;

    @GetMapping
    public List allPatients(){
        // API pour afficher liste des patients
        List<PatientDTO> listPatient=i_patient.showPatient();
        return listPatient;
    }
    @GetMapping("/{id}")
    public PatientDTO showPatient(@PathVariable(value = "id") Long id_Patient){
        // API pour afficher patients par ID
        PatientDTO patient=i_patient.showPatientwithid(id_Patient);
        return patient;
    }
    @PostMapping
    public ResponseEntity<PatientDTO> addPatient(@RequestBody @Valid PatientDTO patientDTO){
        // API pour ajouter patients
        PatientDTO patientDTO1=i_patient.addPatient(patientDTO);
        return new ResponseEntity<>(patientDTO1, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PatientDTO> modPatient(@RequestBody @Valid PatientDTO patientDTO){
        // API pour modifier patients
        PatientDTO patientDTO1=i_patient.modPatient(patientDTO);
        return new ResponseEntity<>(patientDTO1, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public Map<String,Boolean> delPatient(@PathVariable(value = "id") Long id_patient ){
        // API pour supprimer patients
        PatientDTO patientDTO=i_patient.showPatientwithid(id_patient);
        Map<String,Boolean> response=new HashMap<>();
        if(i_patient.delPatient(patientDTO)){
            response.put("deleted",Boolean.TRUE);
        }
        return response;
    }







}
