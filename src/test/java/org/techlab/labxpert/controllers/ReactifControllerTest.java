package org.techlab.labxpert.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.techlab.labxpert.dtos.FournisseurDTO;
import org.techlab.labxpert.dtos.ReactifDTO;
import org.techlab.labxpert.entity.Fournisseur;
import org.techlab.labxpert.service.I_Reactif;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ReactifController.class)
class ReactifControllerTest {

    @MockBean
    I_Reactif i_reactif;
    @Spy
    ModelMapper modelMapper1;
    @Autowired
    MockMvc mockMvc;


    ReactifDTO reactifDTO;
    FournisseurDTO fournisseur;
    SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd");
    @BeforeEach
    void setUp() throws ParseException {
        fournisseur=new FournisseurDTO();
        fournisseur.setIdFournisseur(1L);
        fournisseur.setNom("FourniLabo");
        fournisseur.setAdresse("qwerty");
        fournisseur.setTel("231656546");

        reactifDTO=new ReactifDTO();
        reactifDTO.setIdReactif(1L);
        reactifDTO.setNom("qwerty");
        reactifDTO.setDescription("qwerty");
        reactifDTO.setQuantite(520);
        reactifDTO.setDateExpiration(inputFormat.parse("2025-02-25"));
        reactifDTO.setFournisseur(modelMapper1.map(fournisseur,Fournisseur.class));
    }

    @Test
    void allReactif() throws Exception {
        List<ReactifDTO> reactifs= Arrays.asList(reactifDTO);
        when(i_reactif.showReactif()).thenReturn(reactifs);
        mockMvc.perform(get("/api/v1/Reactif")
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void showReactif() throws Exception {
        when(i_reactif.showReactifwithid(1L)).thenReturn(reactifDTO);
        mockMvc.perform(get("/api/v1/Reactif/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void addReactif() throws Exception {
        when(i_reactif.addReactif(reactifDTO)).thenReturn(reactifDTO);
        mockMvc.perform(post("/api/v1/Reactif")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(reactifDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void modRactif() throws Exception {
        when(i_reactif.modReactif(any(ReactifDTO.class))).thenReturn(reactifDTO);
        mockMvc.perform(put("/api/v1/Reactif")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reactifDTO)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void delReactif() throws Exception{
        when(i_reactif.delReactif(any(ReactifDTO.class))).thenReturn(Boolean.TRUE);
        mockMvc.perform(delete("/api/v1/Reactif/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}