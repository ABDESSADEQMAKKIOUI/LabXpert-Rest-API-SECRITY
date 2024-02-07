package org.techlab.labxpert.service.serviceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.techlab.labxpert.Enum.RoleUser;
import org.techlab.labxpert.Enum.StatutEchantillon;
import org.techlab.labxpert.Enum.StatutNumeration;
import org.techlab.labxpert.dtos.*;
import org.techlab.labxpert.entity.*;
import org.techlab.labxpert.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NumerationServiceTest {

    @Autowired
    I_Analyse i_analyse;
    @Autowired
    I_Echantillon i_echantillon;
    @Autowired
    I_Patient i_patient;
    @Autowired
    I_Utilisateur i_utilisateur;
    @Autowired
    I_Norme i_norme;
    @Autowired
    I_Numeration i_numeration;
    PatientDTO patientDTO;
    UtilisateurDTO utilisateurDTO;
    EchantillonDTO echantillonDTO;
    AnalyseDTO analyseDTO;
    NumerationDTO numerationDTO;
    NormeDTO normeDTO;
    @Autowired
    ModelMapper modelMapper;
    Date date;
    SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd");
    @BeforeEach
    void setUp() throws ParseException {

        patientDTO=new PatientDTO();
        patientDTO.setNom("TALAINI");
        patientDTO.setPrenom("Mohammed");
        patientDTO.setAdresse("Casablanca Bernoussi Bloc 61");
        patientDTO.setTel("0617066494");
        patientDTO.setSexe("Male");
        date = inputFormat.parse("1995-02-28");
        patientDTO.setDateNaissance(date);
        PatientDTO patientDTO1=i_patient.addPatient(patientDTO);
        /***************************************************************/
        utilisateurDTO=new UtilisateurDTO();
        utilisateurDTO.setNom("imad");
        utilisateurDTO.setPrenom("mohssine");
        utilisateurDTO.setAdresse("Casablanca");
        utilisateurDTO.setTel("147852369");
        utilisateurDTO.setSexe("Male");
        date = inputFormat.parse("2000-02-02");
        utilisateurDTO.setDateNaissance(date);
        utilisateurDTO.setPassword("123456");
        utilisateurDTO.setNomUtilisateur("imad");
        utilisateurDTO.setRole(RoleUser.Preleveur);
        UtilisateurDTO utilisateurDTO1=i_utilisateur.addUser(utilisateurDTO);
        /*************************************************************/
        echantillonDTO=new EchantillonDTO();
        echantillonDTO.setPatient(modelMapper.map(patientDTO1, Patient.class));
        echantillonDTO.setStatus(StatutEchantillon.EnAttente);
        echantillonDTO.setUtilisateur(modelMapper.map(utilisateurDTO1, Utilisateur.class));
        date = inputFormat.parse("2024-01-18");
        echantillonDTO.setDatePrelevement(date);
        echantillonDTO.setTypeAnalyse("Biochimie Sanguine");
        EchantillonDTO echantillonDTO1=i_echantillon.addEchantillon(echantillonDTO);
        /****************************************************/
        analyseDTO=new AnalyseDTO();
        analyseDTO.setNomAnalyse(echantillonDTO1.getTypeAnalyse());
        analyseDTO.setEchantillon(modelMapper.map(echantillonDTO1, Echantillon.class));
        analyseDTO.setCommantaire("analyse a faire urgent");
        analyseDTO.setDateDebut(inputFormat.parse("2024-01-22"));
        analyseDTO.setDateFin(inputFormat.parse("2024-01-22"));
        AnalyseDTO analyseDTO1=i_analyse.addAnalyse(analyseDTO);
        /************************************************************/
        normeDTO=new NormeDTO();
        normeDTO.setLibelle("Calcium");
        normeDTO.setUnite("mg/dL");
        normeDTO.setMinValue(8.5);
        normeDTO.setMaxValue(10.5);
        NormeDTO norme=i_norme.addNorme(normeDTO);
        /*************************************************************/
        numerationDTO=new NumerationDTO();
        numerationDTO.setAnalyse(modelMapper.map(analyseDTO1, Analyse.class));
        numerationDTO.setValue(3.1);
        numerationDTO.setNorme(modelMapper.map(norme,Norme.class));
        if(numerationDTO.getValue()>=norme.getMinValue() && numerationDTO.getValue()<=norme.getMaxValue()){
            numerationDTO.setStatut(StatutNumeration.Normal);
        }else {numerationDTO.setStatut(StatutNumeration.Anormal);
        }


    }
    @Test
    void addNumeration() {
        assertNotNull(i_numeration.addNumeration(numerationDTO),"numeration not added");
    }

    @Test
    void modNumeration() {
        NumerationDTO numerationDTO1=i_numeration.addNumeration(numerationDTO);
        double value=numerationDTO1.getValue();
        numerationDTO1.setValue(30);
        numerationDTO1=i_numeration.modNumeration(numerationDTO1);
        assertNotEquals(value,numerationDTO1.getValue());
    }

    @Test
    void numerationWithId() {
        NumerationDTO numerationDTO1=i_numeration.addNumeration(numerationDTO);
        assertNotNull(i_numeration.NumerationWithId(numerationDTO1.getIdNumeration()));
    }

    @Test
    void delNumeration() {
        NumerationDTO numerationDTO1=i_numeration.addNumeration(numerationDTO);
        assertTrue(i_numeration.delNumeration(numerationDTO1));
    }

    @Test
    void allNumerationWithAnalyse() {
        NumerationDTO numerationDTO1=i_numeration.addNumeration(numerationDTO);
        assertNotNull(i_numeration.allNumerationWithAnalyse(modelMapper.map(numerationDTO1.getAnalyse(),AnalyseDTO.class)),"Empty list");
    }

    @Test
    void allNumeration() {
        NumerationDTO numerationDTO1=i_numeration.addNumeration(numerationDTO);
        assertNotNull(i_numeration.allNumeration(),"Empty list");

    }
}