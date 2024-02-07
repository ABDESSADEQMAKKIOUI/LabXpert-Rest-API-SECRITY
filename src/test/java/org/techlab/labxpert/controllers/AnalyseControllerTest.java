package org.techlab.labxpert.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.techlab.labxpert.Enum.RoleUser;
import org.techlab.labxpert.Enum.StatutEchantillon;
import org.techlab.labxpert.dtos.*;
import org.techlab.labxpert.entity.Echantillon;
import org.techlab.labxpert.entity.Patient;
import org.techlab.labxpert.entity.Utilisateur;
import org.techlab.labxpert.service.*;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AnalyseController.class)
class AnalyseControllerTest {

    @MockBean
    I_Analyse i_analyse;
    @MockBean
    I_Echantillon i_echantillon;
    @MockBean
    I_Patient i_patient;
    @MockBean
    I_Utilisateur i_utilisateur;
    @MockBean
    I_Rapport i_rapport;
    @MockBean
    I_Reactif i_reactif;
    @MockBean
    I_ReactifAnalyse i_reactifAnalyse;
    @MockBean
    ModelMapper modelMapper;
    @Autowired
    MockMvc mockMvc;

    PatientDTO patientDTO;
    UtilisateurDTO utilisateurDTO;
    EchantillonDTO echantillonDTO;
    AnalyseDTO analyseDTO;

    Date date;
    SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() throws ParseException {
        patientDTO=new PatientDTO();
        patientDTO.setId(1L);
        patientDTO.setNom("mohammed");
        patientDTO.setPrenom("prenom mohammed");
        patientDTO.setAdresse("qwerty");
        patientDTO.setTel("02125232525");
        patientDTO.setSexe("Male");
        date = inputFormat.parse("2000-02-02");
        patientDTO.setDateNaissance(date);
        //when(i_patient.addPatient(patientDTO)).thenReturn(patientDTO);
        //PatientDTO patientDTO1=i_patient.addPatient(patientDTO);
        /***************************************************************/
        utilisateurDTO=new UtilisateurDTO();
        utilisateurDTO.setIdUtilisateur(1L);
        utilisateurDTO.setNom("imad");
        utilisateurDTO.setPrenom("prenom imad");
        utilisateurDTO.setAdresse("azerty");
        utilisateurDTO.setTel("147852369");
        utilisateurDTO.setSexe("Male");
        date = inputFormat.parse("2000-02-02");
        utilisateurDTO.setDateNaissance(date);
        utilisateurDTO.setPassword("123456");
        utilisateurDTO.setNomUtilisateur("qwerty");
        utilisateurDTO.setRole(RoleUser.Preleveur);
        //when(i_utilisateur.addUser(utilisateurDTO)).thenReturn(utilisateurDTO);
        //UtilisateurDTO utilisateurDTO1=i_utilisateur.addUser(utilisateurDTO);
        /*************************************************************/
        echantillonDTO=new EchantillonDTO();
        echantillonDTO.setIdEchantillon(1L);
        echantillonDTO.setPatient(modelMapper.map(patientDTO, Patient.class));
        echantillonDTO.setStatus(StatutEchantillon.EnAttente);
        echantillonDTO.setUtilisateur(modelMapper.map(utilisateurDTO, Utilisateur.class));
        date = inputFormat.parse("2024-01-18");
        echantillonDTO.setDatePrelevement(date);
        echantillonDTO.setTypeAnalyse("Biochimie");
        //when(i_echantillon.addEchantillon(echantillonDTO)).thenReturn(echantillonDTO);
        //EchantillonDTO echantillonDTO1=i_echantillon.addEchantillon(echantillonDTO);
        /****************************************************/
        analyseDTO=new AnalyseDTO();
        analyseDTO.setIdAnalyse(1L);
        analyseDTO.setNomAnalyse(echantillonDTO.getTypeAnalyse());
        analyseDTO.setEchantillon(modelMapper.map(echantillonDTO, Echantillon.class));
        //analyseDTO.setCommantaire("analyse a faire urgent");
        //analyseDTO.setDateDebut(inputFormat.parse("2024-01-19"));
        //analyseDTO.setDateFin(inputFormat.parse("2024-01-19"));
        //when(i_analyse.addAnalyse(analyseDTO)).thenReturn(analyseDTO);
    }

    @Test
    void getanalyses() throws Exception {
        // Test Pour Verifier La liste des analyses
        List<AnalyseDTO>  analyselist= Arrays.asList(analyseDTO);
        when(i_analyse.showAnalyses()).thenReturn(analyselist);
        MvcResult mvcResult= mockMvc.perform(get("/api/v1/analyse")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertNotNull(response);
    }

    @Test
    void getanalyse() throws Exception {
        // Test Analyse with Id
        when(i_analyse.showAnalyseWithId(1L)).thenReturn(analyseDTO);
        MvcResult mvcResult= mockMvc.perform(get("/api/v1/analyse/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertNotNull(response);
    }


    @Test
    void updateAnalyse() throws Exception {
        // Test Modification Analyse
        when(i_analyse.showAnalyseWithId(1L)).thenReturn(analyseDTO);
        analyseDTO.setCommantaire("analyse a faire urgent");
        analyseDTO.setDateDebut(inputFormat.parse("2024-01-19"));
        analyseDTO.setDateFin(inputFormat.parse("2024-01-19"));
        when(i_analyse.modAnalyse(any(AnalyseDTO.class))).thenReturn(analyseDTO);
        MvcResult mvcResult= mockMvc.perform(put("/api/v1/analyse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(analyseDTO)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertNotNull(response);
    }

    @Test
    void deleteAnalyse() throws Exception {
        // Test Suppression Analyse
        when(i_analyse.delAnalyse(any(AnalyseDTO.class))).thenReturn(Boolean.TRUE);
        MvcResult mvcResult= mockMvc.perform(delete("/api/v1/analyse/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        System.out.println(response);
        assertNotNull(response);
    }

}