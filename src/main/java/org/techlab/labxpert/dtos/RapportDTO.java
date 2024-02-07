package org.techlab.labxpert.dtos;

import lombok.Data;
import org.techlab.labxpert.Enum.StatutEchantillon;
import org.techlab.labxpert.Enum.StatutNumeration;

import java.util.Date;
@Data
public class RapportDTO {
    private String nom_analyse;
    private String nom;
    private String prenom;
    private String tel;
    private String adresse;
    private Date date_naissance;
    private String statut;
    private double value;
    private String libelle;
    private double max_value;
    private double min_value;
    private  String unite;

}
