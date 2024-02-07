package org.techlab.labxpert.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.techlab.labxpert.Enum.RoleUser;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
@Data
public class UtilisateurDTO {
    private Long idUtilisateur;
    @NotNull(message = "Nom Utilisateur est null")
    private String nom;
    @NotNull(message ="Prenom Utilisateur est null" )
    private String prenom;
    @NotNull(message ="DateNaissance Utilisateur est null" )
    @Past
    private Date dateNaissance;
    @NotNull(message ="Sexe Utilisateur est null" )
    private String sexe;
    @NotNull(message ="Adresse Utilisateur est null" )
    private String adresse;
    @NotNull(message ="Tel Utilisateur est null" )
    private String tel;
    @NotNull(message ="Role Utilisateur est null" )
    private RoleUser role;
    @NotNull(message ="Password Utilisateur est null" )
    private String password;
    @NotNull(message ="Username est null" )
    private String nomUtilisateur;
    private boolean deleted;

}
