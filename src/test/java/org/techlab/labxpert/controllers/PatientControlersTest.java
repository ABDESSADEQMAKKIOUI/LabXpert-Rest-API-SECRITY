package org.techlab.labxpert.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.techlab.labxpert.dtos.EchantillonDTO;
import org.techlab.labxpert.dtos.PatientDTO;
import org.techlab.labxpert.dtos.UtilisateurDTO;
import org.techlab.labxpert.service.serviceImp.EchantillonServiceImp;
import org.techlab.labxpert.service.serviceImp.PatientServiceImp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
@ExtendWith(SpringExtension.class)
public class PatientControlersTest {
    @MockBean
PatientServiceImp patientServiceImp;
    @MockBean
    ModelMapper modelMapper;
    @Autowired
    MockMvc mockMvc;

PatientDTO patientDTO;
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
    @Test
    public void test_savePatient() throws Exception {
        when(patientServiceImp.addPatient(patientDTO)).thenReturn(patientDTO);
        mockMvc.perform(post("/api/v1/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patientDTO)))
                .andExpect(status().isCreated());
    }
    @Test
    public  void test_getControllertList() throws Exception {
        List<PatientDTO> patientList =new ArrayList<>();

        when(patientServiceImp.showPatient()).thenReturn(patientList);
        mockMvc.perform(get("/api/v1/patient")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void test_deleteContoller() throws Exception {
        // Mock the behavior of delNorme to do nothing when called with normeDTO
        doReturn(true).when(patientServiceImp).delPatient(patientDTO);

        mockMvc.perform(delete("/api/v1/patient/{id}", 1)  // Replace '1' with the actual ID
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patientDTO)))
                .andExpect(status().isOk());
    }

}
