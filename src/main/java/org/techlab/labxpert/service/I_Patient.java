package org.techlab.labxpert.service;


import org.techlab.labxpert.dtos.PatientDTO;
import org.techlab.labxpert.entity.Patient;

import java.util.List;

public interface I_Patient {
    public PatientDTO addPatient(PatientDTO patientdto);
    public List<PatientDTO> showPatient();
    public PatientDTO showPatientwithid(Long id);
    public PatientDTO modPatient(PatientDTO patientdto);
    public boolean delPatient(PatientDTO patientdto);

}
