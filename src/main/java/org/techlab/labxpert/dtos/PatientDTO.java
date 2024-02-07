package org.techlab.labxpert.dtos;

import lombok.Data;
import org.techlab.labxpert.entity.Echantillon;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Data

public class PatientDTO {
    private Long id;
    @NotNull(message ="nom est null" )
    private String nom;
    @NotNull(message ="prenom est null" )
    private String prenom;
    @NotNull(message ="dateNaissance est null" )
    @Past
    private Date dateNaissance;
    @NotNull(message ="sexe est null" )
    private String sexe;
    @NotNull(message ="adresse est null" )
    private String adresse;
    @NotNull(message ="tel est null" )
    private String tel;
    private List<Echantillon> echantillons;
    private boolean deleted;
}
