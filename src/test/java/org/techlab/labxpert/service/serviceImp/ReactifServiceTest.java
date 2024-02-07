package org.techlab.labxpert.service.serviceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.techlab.labxpert.dtos.EchantillonDTO;
import org.techlab.labxpert.dtos.ReactifDTO;
import org.techlab.labxpert.entity.Fournisseur;
import org.techlab.labxpert.entity.Numeration;
import org.techlab.labxpert.entity.Reactif;

import org.techlab.labxpert.repository.FournisseurRepository;
import org.techlab.labxpert.service.I_Reactif;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReactifServiceTest {

    @Autowired
    I_Reactif i_reactif;
    ReactifDTO reactifDTO;
    @Autowired
    FournisseurRepository fournisseurRepository;
    Fournisseur fournisseur;

    /*@ParameterizedTest
    @CsvFileSource(resources = "/listreactif.csv" , numLinesToSkip = 1)
    void addReactif(String Name,String Description,int Quantite,String DateExperation,Long idfournisseur) throws ParseException {
        Reactif reactif=new Reactif();
        reactif.setNom(Name);
        reactif.setDescription(Description);
        reactif.setQuantite(Quantite);
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = inputFormat.parse(DateExperation);
        reactif.setDateExpiration(date);
        Fournisseur fournisseur=fournisseurRepository.findById(idfournisseur).get();
        reactif.setFournisseur(fournisseur);
        i_reactif.addReactif(reactif);
    }*/
    Date date;
    SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd");
    @BeforeEach
    void setUp() throws ParseException {
        fournisseur=new Fournisseur(2L,"ahmed","fes","03299877",Boolean.FALSE);
     Fournisseur  fournisseur1=fournisseurRepository.save(fournisseur);

        reactifDTO=new ReactifDTO("reactif1","diabetique",22, inputFormat.parse("2000-02-02"), fournisseur1);
    }


    @Test
    void addReactif(){
        System.out.println(reactifDTO);
        ReactifDTO reactif1= i_reactif.addReactif( reactifDTO);
        assertNotNull(reactif1);
    }
    @Test
    void showReactiList(){
     List<ReactifDTO> reactifDTO2=i_reactif.showReactif();
        assertNotNull(reactifDTO2);
    }
    @Test
    void showReactifwithId(){
        ReactifDTO reactif1= i_reactif.addReactif( reactifDTO);
        ReactifDTO reactifDTO3=i_reactif.showReactifwithid(reactif1.getIdReactif());
        assertNotNull( reactifDTO3);
    }

    @Test
    void delReactif() {
        ReactifDTO reactifDTO1=i_reactif.addReactif(reactifDTO);
        assertTrue(i_reactif.delReactif(reactifDTO1));
    }

}