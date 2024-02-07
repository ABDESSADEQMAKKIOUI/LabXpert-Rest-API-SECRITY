package org.techlab.labxpert.service;

import org.techlab.labxpert.dtos.AnalyseDTO;
import org.techlab.labxpert.dtos.NumerationDTO;
import org.techlab.labxpert.entity.Numeration;

import java.util.List;

public interface I_Numeration {

    NumerationDTO addNumeration(NumerationDTO numerationDTO);
    NumerationDTO modNumeration(NumerationDTO numerationDTO);
    NumerationDTO NumerationWithId(Long id);

    boolean delNumeration(NumerationDTO numerationDTO);
    List<NumerationDTO> allNumerationWithAnalyse(AnalyseDTO analyseDTO);
    List<NumerationDTO> allNumeration();

}
