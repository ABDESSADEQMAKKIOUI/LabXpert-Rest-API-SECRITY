package org.techlab.labxpert.service.serviceImp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.techlab.labxpert.dtos.PatientDTO;
import org.techlab.labxpert.service.I_Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PatientServiceTest {

    @Autowired
    I_Patient i_patient;

    PatientDTO patientDTO;
    @Autowired
    ModelMapper modelMapper;
    Date date;
    SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd");
    @BeforeEach
    void setUp() throws ParseException {
        patientDTO=new PatientDTO();
        patientDTO.setNom("mohammed");
        patientDTO.setPrenom("prenom mohammed");
        patientDTO.setAdresse("qwerty");
        patientDTO.setTel("02125232525");
        patientDTO.setSexe("Male");
        date = inputFormat.parse("2000-02-02");
        patientDTO.setDateNaissance(date);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Tag("test1")
    void addPatient() {
        PatientDTO patientDTO1=i_patient.addPatient(patientDTO);
        assertNotNull(patientDTO1,"Patient not inserted");

    }

    @Test
    @Tag("test1")
    void showPatient() {
        PatientDTO patientDTO1=i_patient.addPatient(patientDTO);
        List<PatientDTO> patients=i_patient.showPatient();
        assertNotNull(patients,"List is Empty");

    }

    @Test
    void showPatientwithid() {
        PatientDTO patientDTO1=i_patient.addPatient(patientDTO);
        assertNotNull(patientDTO1,"Patient not Found");

    }

    @Test
    void modPatient() {
        PatientDTO patientDTO1=i_patient.addPatient(patientDTO);
        String name=patientDTO1.getNom();
        patientDTO1.setNom("morad");
        patientDTO1=i_patient.modPatient(patientDTO1);
        assertNotEquals(name,patientDTO1.getNom());
    }

    @Test
    void delPatient() {
        PatientDTO patientDTO1=i_patient.addPatient(patientDTO);
        assertTrue(i_patient.delPatient(patientDTO1));
    }
}