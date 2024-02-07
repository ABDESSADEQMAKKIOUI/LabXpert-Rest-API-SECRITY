package org.techlab.labxpert.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.techlab.labxpert.Enum.RoleUser;
import org.techlab.labxpert.Enum.StatutEchantillon;
import org.techlab.labxpert.Enum.StatutNumeration;
import org.techlab.labxpert.dtos.*;
import org.techlab.labxpert.entity.*;
import org.techlab.labxpert.service.I_Analyse;
import org.techlab.labxpert.service.I_Numeration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(NumerationController.class)
class NumerationControllerTest {

    @MockBean
    I_Numeration i_numeration;
    @MockBean
    I_Analyse i_analyse;
    @Spy
    ModelMapper modelMapper;
    @Autowired
    MockMvc mockMvc;

    NumerationDTO numerationDTO;
    PatientDTO patientDTO;
    UtilisateurDTO utilisateurDTO;
    EchantillonDTO echantillonDTO;
    AnalyseDTO analyseDTO;
    NormeDTO normeDTO;

    Date date;
    SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd");
    @BeforeEach
    void setUp() throws Exception{
        patientDTO=new PatientDTO();
        patientDTO.setId(1L);
        patientDTO.setNom("mohammed");
        patientDTO.setPrenom("prenom mohammed");
        patientDTO.setAdresse("qwerty");
        patientDTO.setTel("02125232525");
        patientDTO.setSexe("Male");
        date = inputFormat.parse("2000-02-02");
        patientDTO.setDateNaissance(date);
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
        /*************************************************************/
        echantillonDTO=new EchantillonDTO();
        echantillonDTO.setIdEchantillon(1L);
        echantillonDTO.setPatient(modelMapper.map(patientDTO, Patient.class));
        echantillonDTO.setStatus(StatutEchantillon.EnAttente);
        echantillonDTO.setUtilisateur(modelMapper.map(utilisateurDTO, Utilisateur.class));
        date = inputFormat.parse("2024-01-18");
        echantillonDTO.setDatePrelevement(date);
        echantillonDTO.setTypeAnalyse("Biochimie");
        /****************************************************/
        analyseDTO=new AnalyseDTO();
        analyseDTO.setIdAnalyse(1L);
        analyseDTO.setNomAnalyse(echantillonDTO.getTypeAnalyse());
        analyseDTO.setEchantillon(modelMapper.map(echantillonDTO, Echantillon.class));
        analyseDTO.setCommantaire("analyse a faire urgent");
        analyseDTO.setDateDebut(inputFormat.parse("2024-01-19"));
        analyseDTO.setDateFin(inputFormat.parse("2024-01-19"));
        /************************************************************/
        normeDTO=new NormeDTO();
        normeDTO.setIdNorme(1L);
        normeDTO.setLibelle("Calcium");
        normeDTO.setUnite("mg/dL");
        normeDTO.setMinValue(8.5);
        normeDTO.setMaxValue(10.5);
        /*************************************************************/
        numerationDTO=new NumerationDTO();
        numerationDTO.setIdNumeration(1L);
        numerationDTO.setAnalyse(modelMapper.map(analyseDTO, Analyse.class));
        numerationDTO.setValue(9);
        numerationDTO.setNorme(modelMapper.map(normeDTO, Norme.class));
        if(numerationDTO.getValue()>normeDTO.getMinValue() && numerationDTO.getValue()<normeDTO.getMaxValue()){
            numerationDTO.setStatut(StatutNumeration.Normal);
        }else {numerationDTO.setStatut(StatutNumeration.Anormal);
        }
    }

    @Test
    void showNumerations() throws Exception {
        List<NumerationDTO> numerationDTOS= Arrays.asList(numerationDTO);
        when(i_numeration.allNumeration()).thenReturn(numerationDTOS);
        mockMvc.perform(get("/api/v1/numeration")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void showNumerationsWithAnalyse() throws Exception {
        List<NumerationDTO> numerationDTOS= Arrays.asList(numerationDTO);
        when(i_analyse.showAnalyseWithId(1L)).thenReturn(analyseDTO);
        when(i_numeration.allNumerationWithAnalyse(any(AnalyseDTO.class))).thenReturn(numerationDTOS);
        mockMvc.perform(get("/api/v1/numeration/analyse/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void addNumeration() throws Exception {
        when(i_numeration.addNumeration(any(NumerationDTO.class))).thenReturn(numerationDTO);
        mockMvc.perform(post("/api/v1/numeration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(numerationDTO)))
                .andExpect(status().isCreated()).andDo(print());
    }

    @Test
    void modNumeration() throws Exception {
        numerationDTO.setValue(25);
        when(i_numeration.modNumeration(any(NumerationDTO.class))).thenReturn(numerationDTO);
        mockMvc.perform(put("/api/v1/numeration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(numerationDTO)))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void deleteNumeration() throws Exception {
        when(i_numeration.NumerationWithId(1L)).thenReturn(numerationDTO);
        when(i_numeration.delNumeration(any(NumerationDTO.class))).thenReturn(numerationDTO.isDeleted());
        mockMvc.perform(delete("/api/v1/numeration/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(numerationDTO)))
                .andExpect(status().isOk()).andDo(print());
    }
}