package org.techlab.labxpert.dtos;
import lombok.Data;
import org.techlab.labxpert.entity.Fournisseur;

import javax.validation.constraints.NotNull;

@Data
public class OutilDTO {
    private Long idOutil;
    @NotNull(message ="libelle est null" )
    private String libelle;
    @NotNull(message ="quantite est null" )
    private int quantite;
    @NotNull(message ="fournisseur est null" )
    private Fournisseur fournisseur;
    private boolean deleted;
}
