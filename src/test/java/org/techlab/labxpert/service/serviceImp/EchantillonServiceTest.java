package org.techlab.labxpert.service.serviceImp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.techlab.labxpert.Enum.RoleUser;
import org.techlab.labxpert.Enum.StatutEchantillon;
import org.techlab.labxpert.dtos.EchantillonDTO;
import org.techlab.labxpert.dtos.PatientDTO;
import org.techlab.labxpert.dtos.UtilisateurDTO;
import org.techlab.labxpert.entity.Patient;
import org.techlab.labxpert.entity.Utilisateur;
import org.techlab.labxpert.service.I_Echantillon;
import org.techlab.labxpert.service.I_Patient;
import org.techlab.labxpert.service.I_Utilisateur;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EchantillonServiceTest {

    @Autowired
    I_Echantillon i_echantillon;
    @Autowired
    I_Patient i_patient;
    @Autowired
    I_Utilisateur i_utilisateur;

    PatientDTO patientDTO;
    UtilisateurDTO utilisateurDTO;
    EchantillonDTO echantillonDTO;
    @Autowired
    ModelMapper modelMapper;
    @BeforeEach
    void setUp() throws ParseException {
        Date date;
        SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd");;
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
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addEchantillon() {
        EchantillonDTO echantillonDTO1=i_echantillon.addEchantillon(echantillonDTO);
        System.out.println(echantillonDTO1);
        assertNotNull(echantillonDTO1,"Echatillon not inserted");
    }

    @Test
    void showEhantillon() {
        List<EchantillonDTO> echantillonDTOS=i_echantillon.showEhantillon();
        assertNotNull(echantillonDTOS,"List is empty");
    }

    @Test
    void showEchantillonwithid() {
        EchantillonDTO echantillonDTO1=i_echantillon.addEchantillon(echantillonDTO);
        EchantillonDTO echantillonDTO2=i_echantillon.showEchantillonwithid(echantillonDTO1.getIdEchantillon());
        assertNotNull(echantillonDTO2,"echantillon not found");
    }

    @Test
    void modEchantillon() {
        EchantillonDTO echantillonDTO1=i_echantillon.addEchantillon(echantillonDTO);
        echantillonDTO1.setStatus(StatutEchantillon.EnCours);
        assertNotNull(i_echantillon.modEchantillon(echantillonDTO1),"not updated");
    }

    @Test
    void delEchantillhon() {
        EchantillonDTO echantillonDTO1=i_echantillon.addEchantillon(echantillonDTO);
        //System.out.println(echantillonDTO1);
        assertTrue(i_echantillon.delEchantillhon(echantillonDTO1));
    }
}