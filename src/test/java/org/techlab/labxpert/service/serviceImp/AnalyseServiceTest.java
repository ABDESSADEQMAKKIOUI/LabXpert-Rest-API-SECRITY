package org.techlab.labxpert.service.serviceImp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.techlab.labxpert.Enum.RoleUser;
import org.techlab.labxpert.Enum.StatutEchantillon;
import org.techlab.labxpert.dtos.AnalyseDTO;
import org.techlab.labxpert.dtos.EchantillonDTO;
import org.techlab.labxpert.dtos.PatientDTO;
import org.techlab.labxpert.dtos.UtilisateurDTO;
import org.techlab.labxpert.entity.Echantillon;
import org.techlab.labxpert.entity.Patient;
import org.techlab.labxpert.entity.Utilisateur;
import org.techlab.labxpert.service.I_Analyse;
import org.techlab.labxpert.service.I_Echantillon;
import org.techlab.labxpert.service.I_Patient;
import org.techlab.labxpert.service.I_Utilisateur;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnalyseServiceTest {
    @Autowired
    I_Analyse i_analyse;

    @Autowired
    I_Echantillon i_echantillon;
    @Autowired
    I_Patient i_patient;
    @Autowired
    I_Utilisateur i_utilisateur;

    PatientDTO patientDTO;
    UtilisateurDTO utilisateurDTO;
    EchantillonDTO echantillonDTO;
    AnalyseDTO analyseDTO;
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
        PatientDTO patientDTO1=i_patient.addPatient(patientDTO);
        /***************************************************************/
        utilisateurDTO=new UtilisateurDTO();
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
        UtilisateurDTO utilisateurDTO1=i_utilisateur.addUser(utilisateurDTO);
        /*************************************************************/
        echantillonDTO=new EchantillonDTO();
        echantillonDTO.setPatient(modelMapper.map(patientDTO1, Patient.class));
        echantillonDTO.setStatus(StatutEchantillon.EnAttente);
        echantillonDTO.setUtilisateur(modelMapper.map(utilisateurDTO1, Utilisateur.class));
        date = inputFormat.parse("2024-01-18");
        echantillonDTO.setDatePrelevement(date);
        echantillonDTO.setTypeAnalyse("Biochimie");
        EchantillonDTO echantillonDTO1=i_echantillon.addEchantillon(echantillonDTO);
        /****************************************************/
        analyseDTO=new AnalyseDTO();
        analyseDTO.setNomAnalyse(echantillonDTO1.getTypeAnalyse());
        analyseDTO.setEchantillon(modelMapper.map(echantillonDTO1, Echantillon.class));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addAnalyse() {
        analyseDTO=i_analyse.addAnalyse(analyseDTO);
        assertNotNull(analyseDTO,"Analyse not inserted");
    }

    @Test
    void modAnalyse() throws ParseException {
        analyseDTO=i_analyse.addAnalyse(analyseDTO);
        analyseDTO.setDateDebut(inputFormat.parse("2024-01-18"));
        analyseDTO.setCommantaire("analyse");
        analyseDTO=i_analyse.modAnalyse(analyseDTO);
        assertNotNull(analyseDTO.getCommantaire(),"update failed");
    }

    @Test
    void showAnalyses() {
        analyseDTO=i_analyse.addAnalyse(analyseDTO);
        List<AnalyseDTO> analyseDTOS=i_analyse.showAnalyses();
        assertNotNull(analyseDTOS);
    }

    @Test
    void showAnalyseWithId() {
        analyseDTO=i_analyse.addAnalyse(analyseDTO);
        AnalyseDTO analyseDTO1=i_analyse.showAnalyseWithId(analyseDTO.getIdAnalyse());
        assertNotNull(analyseDTO1);
    }

//    @Test
//    void delAnalyse() {
//        analyseDTO=i_analyse.addAnalyse(analyseDTO);
//        assertTrue(i_analyse.delAnalyse(analyseDTO));
//    }

}
