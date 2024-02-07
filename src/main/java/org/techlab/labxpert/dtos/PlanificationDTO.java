package org.techlab.labxpert.dtos;

import lombok.Data;
import org.techlab.labxpert.entity.Analyse;
import org.techlab.labxpert.entity.Utilisateur;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PlanificationDTO {
    private Long idPlanification;
    @NotNull(message ="analyse est null" )
    private Analyse analyse;
    @NotNull(message ="utilisateur est null" )
    private Utilisateur utilisateur;
    @NotNull(message ="dateDebut est null" )
    private Date dateDebut;
    @NotNull(message ="dateFin est null" )
    private Date dateFin;
    private boolean deleted;
}
