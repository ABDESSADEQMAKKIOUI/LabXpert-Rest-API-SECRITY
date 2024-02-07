package org.techlab.labxpert.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.techlab.labxpert.Enum.RoleUser;
import org.techlab.labxpert.dtos.NormeDTO;
import org.techlab.labxpert.dtos.UtilisateurDTO;

import org.techlab.labxpert.service.serviceImp.UtilisateurServiceImp;

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

@WebMvcTest(UtilisateurController.class)
@ExtendWith(SpringExtension.class)
public class UtilisateurControlersTest {
    @MockBean
    UtilisateurServiceImp utilisateurService;
    @MockBean
    ModelMapper modelMapper;
    @Autowired
    MockMvc mockMvc;
    UtilisateurDTO utilisateurDTO;

    @BeforeEach
    void setUp() throws ParseException {
        String dateString = "02/02/1995";
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = inputFormat.parse(dateString);
        utilisateurDTO=new UtilisateurDTO();
        utilisateurDTO.setNom("med");
        utilisateurDTO.setPrenom("imad");
        utilisateurDTO.setDateNaissance(date);
        utilisateurDTO.setSexe("male");
        utilisateurDTO.setAdresse("qwerty");
        utilisateurDTO.setTel("03251325625");
        utilisateurDTO.setRole(RoleUser.Technicien);
        utilisateurDTO.setPassword("12346");
        utilisateurDTO.setNomUtilisateur("medtala");
    }

@Test
   public void test_saveUtilisateur() throws Exception {
        when(utilisateurService.addUser(utilisateurDTO)).thenReturn(utilisateurDTO);
        mockMvc.perform(post("/api/v1/utilisateur")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(utilisateurDTO)))
                .andExpect(status().isCreated());
    }
    @Test
    public  void test_getControllertList() throws Exception {
        List<UtilisateurDTO> utilisateurList =new ArrayList<>();

        when(utilisateurService.showUsers()).thenReturn(utilisateurList);
        mockMvc.perform(get("/api/v1/utilisateur")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void test_deleteContoller() throws Exception {
        // Mock the behavior of delNorme to do nothing when called with normeDTO
        doReturn(true).when(utilisateurService).delUser(utilisateurDTO);

        mockMvc.perform(delete("/api/v1/utilisateur/{id}", 1)  // Replace '1' with the actual ID
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(utilisateurDTO)))
                .andExpect(status().isOk());
    }








}
