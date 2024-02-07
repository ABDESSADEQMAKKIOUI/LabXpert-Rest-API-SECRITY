package org.techlab.labxpert.service.serviceImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techlab.labxpert.dtos.PatientDTO;
import org.techlab.labxpert.dtos.UtilisateurDTO;
import org.techlab.labxpert.entity.Patient;
import org.techlab.labxpert.entity.Utilisateur;
import org.techlab.labxpert.repository.PatientRepository;
import org.techlab.labxpert.service.I_Patient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImp implements I_Patient{
    @Autowired
    PatientRepository patientRepository;
    ModelMapper modelMapper=new ModelMapper();
    @Override
    public PatientDTO addPatient(PatientDTO patientdto) {
        Patient patient=patientRepository.save(modelMapper.map(patientdto,Patient.class));
        return modelMapper.map(patient,PatientDTO.class);
    }

    @Override
    public List<PatientDTO> showPatient() {
        List<Patient> patients=patientRepository.findByDeletedFalse();
        return patients.stream().map(user->modelMapper.map(user,PatientDTO.class)).collect(Collectors.toList());
    }
    @Override
    public PatientDTO showPatientwithid(Long id) {
        Patient patient =patientRepository.findById(id).get();
        return modelMapper.map(patient,PatientDTO.class);
    }
    @Override
    public PatientDTO modPatient(PatientDTO patientdto) {
        Patient patient=patientRepository.save(modelMapper.map(patientdto,Patient.class));
        return modelMapper.map(patient,PatientDTO.class);
    }

    @Override
    public boolean delPatient(PatientDTO patientdto) {
        patientdto.setDeleted(Boolean.TRUE);
        Patient patient=patientRepository.save(modelMapper.map(patientdto,Patient.class));
        return patient.isDeleted();
    }
}
