package org.techlab.labxpert.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class FournisseurDTO {
    private Long idFournisseur;
    @NotBlank(message="nom null")
    private String nom;
    @NotBlank(message="nom null")
    private String adresse;
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    private String tel;

    private boolean deleted;
}
