package org.techlab.labxpert.dtos;

import lombok.Data;

import org.techlab.labxpert.entity.Echantillon;
import org.techlab.labxpert.entity.ReactifAnalyse;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Collection;
import java.util.Date;

@Data
public class AnalyseDTO {
    private Long idAnalyse;
    //@NotNull(message="nom null")
    private Echantillon echantillon;
    //@NotBlank(message="nom null")
    private String nomAnalyse;
    //@NotNull(message="nom null")
    private Date dateDebut;
   // @NotNull(message="nom null")
    private Date dateFin;
   // @NotBlank(message="nom null")
    private String commantaire;
    private Collection<ReactifAnalyse> reactifAnalyseList;
    private boolean deleted;
}
