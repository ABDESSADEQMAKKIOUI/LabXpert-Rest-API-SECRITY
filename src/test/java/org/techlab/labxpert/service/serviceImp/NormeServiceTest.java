package org.techlab.labxpert.service.serviceImp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.techlab.labxpert.dtos.NormeDTO;
import org.techlab.labxpert.dtos.ReactifDTO;
import org.techlab.labxpert.entity.Norme;
import org.techlab.labxpert.repository.NormeRepository;
import org.techlab.labxpert.service.I_Norme;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class NormeServiceTest {

    @Autowired
    NormeRepository normeRepository;
    @Autowired
    NormeServiceImp normeServiceImp;
    @Autowired
    I_Norme i_norme;

    NormeDTO normeDTO;
    @BeforeEach
    void setUp() {
        normeDTO=new NormeDTO();
        normeDTO.setLibelle("moyen");
        normeDTO.setUnite("ml");
        normeDTO.setMinValue(0.5);
        normeDTO.setMaxValue(2.5);
    }

    @AfterEach
    void tearDown() {
    }
  @ParameterizedTest
    @CsvFileSource(resources = "/listTest.csv", numLinesToSkip = 1)
    void addNorme(String Name,double Min,double Max,String Unit) {
        Norme norme=new Norme();
        norme.setLibelle(Name);
        norme.setUnite(Unit);
        norme.setMaxValue(Max);
        norme.setMinValue(Min);
        normeRepository.save(norme);
    }

    @Test
    void showNormes() {
        System.out.println(normeRepository.findAll());
    }
    @Test
    void addNorme(){
        System.out.println(normeDTO);
        NormeDTO norme1= i_norme.addNorme( normeDTO);
        assertNotNull(norme1);
    }
    @Test
    void showNorme(){
        List<NormeDTO> normeDTO2=i_norme.showNormes();
        assertNotNull(normeDTO2);
    }

    @Test
    void showNormewithId(){
        NormeDTO norme1= i_norme.addNorme( normeDTO);
        NormeDTO normeDTO3=i_norme.getNormeById(norme1.getIdNorme());
        assertNotNull( normeDTO3);
    }
    @Test
    void delNorme() {
        NormeDTO normeDTO1=i_norme.addNorme(normeDTO);


        assertTrue(i_norme.delNorme(normeDTO1));
    }

}
