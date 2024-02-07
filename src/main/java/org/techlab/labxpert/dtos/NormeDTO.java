package org.techlab.labxpert.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class NormeDTO {
    private Long idNorme;
    @NotBlank(message="nom null")
    private String libelle;
    @NotBlank(message="nom null")
    private  String unite;
    @NotNull(message="nom null")
    private double maxValue;
    @NotNull(message="nom null")
    private double minValue;
    private boolean deleted;
}
