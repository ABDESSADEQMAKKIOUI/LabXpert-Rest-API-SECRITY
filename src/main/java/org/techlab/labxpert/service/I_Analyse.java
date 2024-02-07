package org.techlab.labxpert.service;


import org.techlab.labxpert.dtos.AnalyseDTO;
import org.techlab.labxpert.dtos.PlanificationDTO;
import org.techlab.labxpert.entity.Analyse;
import org.techlab.labxpert.entity.Echantillon;

import java.util.List;

public interface I_Analyse {
    public AnalyseDTO addAnalyse(AnalyseDTO analyseDTO);
    public AnalyseDTO modAnalyse(AnalyseDTO analyseDTO);
    public boolean delAnalyse(AnalyseDTO analyseDTO);
    public List<AnalyseDTO> showAnalyses();
    public AnalyseDTO showAnalyseWithId(Long id);
    public PlanificationDTO planifierAnalyse(PlanificationDTO planificationDTO);
    public List<Object[]> printResultRepport(Long id);
}
