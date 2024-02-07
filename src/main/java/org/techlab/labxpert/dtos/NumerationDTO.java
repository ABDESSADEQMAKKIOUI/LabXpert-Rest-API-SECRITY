package org.techlab.labxpert.dtos;

import lombok.Data;
import org.techlab.labxpert.Enum.StatutNumeration;
import org.techlab.labxpert.entity.Analyse;
import org.techlab.labxpert.entity.Norme;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
public class NumerationDTO {
    private Long idNumeration;
    private double value;
    //@NotNull(message="nom null")
    private StatutNumeration statut;
    @NotNull(message="not null")
    private Norme norme;
    @NotNull(message="not null")
    private Analyse analyse;
    private boolean deleted;
}
