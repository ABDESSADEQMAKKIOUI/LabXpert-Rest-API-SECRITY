package org.techlab.labxpert.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.techlab.labxpert.entity.Analyse;
import org.techlab.labxpert.entity.Fournisseur;
import org.techlab.labxpert.entity.Reactif;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@NoArgsConstructor
public class ReactifDTO {
    private Long idReactif;
    @NotNull(message ="nom Reactif est null" )
    private String nom;
    @NotNull(message ="description Reactif est null" )
    private String description;
    @NotNull(message ="quantite Reactif est null" )
    private int quantite;
    @NotNull(message ="dateExpiration Reactif est null" )
    private Date dateExpiration;
    @NotNull(message ="fournisseur est null" )
    private Fournisseur fournisseur;
    private boolean deleted;

    public ReactifDTO(String nom, String description, int quantite, Date dateExpiration, Fournisseur fournisseur) {
        this.nom = nom;
        this.description = description;
        this.quantite = quantite;
        this.dateExpiration = dateExpiration;
        this.fournisseur = fournisseur;
    }


}
