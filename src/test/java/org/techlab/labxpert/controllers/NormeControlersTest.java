package org.techlab.labxpert.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.techlab.labxpert.dtos.NormeDTO;
import org.techlab.labxpert.service.serviceImp.NormeServiceImp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NormeController.class)
@ExtendWith(SpringExtension.class)
public class NormeControlersTest {

    @MockBean
    NormeServiceImp normeServiceImp;
    @Spy
    ModelMapper modelMapper;
    @Autowired
    MockMvc mockMvc;
    NormeDTO normeDTO;

    @BeforeEach
    void setUp() {
        normeDTO = new NormeDTO();
        normeDTO.setLibelle("moyen");
        normeDTO.setUnite("ml");
        normeDTO.setMinValue(0.5);
        normeDTO.setMaxValue(2.5);
    }

    @Test
    public void test_saveNorme() throws Exception {
        when(normeServiceImp.addNorme(normeDTO)).thenReturn(normeDTO);
        mockMvc.perform(post("/api/v1/norme")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(normeDTO)))
                .andExpect(status().isCreated());
    }
    @Test
    public  void test_getNorme() throws Exception {
        List<NormeDTO > normList =new ArrayList<>();

        when(normeServiceImp.showNormes()).thenReturn(normList);
        mockMvc.perform(get("/api/v1/norme")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void test_deleteNorme() throws Exception {
        // Mock the behavior of delNorme to do nothing when called with normeDTO
        doReturn(true).when(normeServiceImp).delNorme(normeDTO);

        mockMvc.perform(delete("/api/v1/norme/{id}", 1)  // Replace '1' with the actual ID
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(normeDTO)))
                .andExpect(status().isOk());
    }



}
