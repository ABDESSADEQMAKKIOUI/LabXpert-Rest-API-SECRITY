package org.techlab.labxpert.serviceImp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.techlab.labxpert.Enum.RoleUser;
import org.techlab.labxpert.dtos.ReactifDTO;
import org.techlab.labxpert.dtos.UtilisateurDTO;
import org.techlab.labxpert.entity.Utilisateur;
import org.techlab.labxpert.service.I_Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UtilisateurServiceTest {

    @Autowired
    I_Utilisateur i_utilisateur;

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
    void addUtilisateur(){

        UtilisateurDTO utilisateur1= i_utilisateur.addUser( utilisateurDTO);
        assertNotNull(utilisateur1);
    }
    @Test
    void showUtilisateurList(){
        List<UtilisateurDTO> utilisateurDTO2=i_utilisateur.showUsers();
        assertNotNull(utilisateurDTO2);
    }
    @Test
    void showUserwithId(){
        UtilisateurDTO utilisateurDTO1= i_utilisateur.addUser(utilisateurDTO);
        UtilisateurDTO utilisateurDTO3=i_utilisateur.showUserwithid(utilisateurDTO1.getIdUtilisateur());
        assertNotNull( utilisateurDTO3);
    }

    @Test
    void delUtilisateur() {
        UtilisateurDTO utilisateurDTO1=i_utilisateur.addUser(utilisateurDTO);


        assertTrue(i_utilisateur.delUser(utilisateurDTO1));
    }



    @AfterEach
    void tearDown() {
    }

    @Test
    void ajouterUtilisateur() {
       //Utilisateur u=i_utilisateur.addUser(utilisateur);
       // assertNotNull(u);
    }

    @Test
    void afficherUtilisateurs() {

        //System.out.println(i_utilisateur.showUsers().toString());
    }
}