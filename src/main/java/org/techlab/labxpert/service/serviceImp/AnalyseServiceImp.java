package org.techlab.labxpert.service.serviceImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techlab.labxpert.dtos.AnalyseDTO;
import org.techlab.labxpert.dtos.PlanificationDTO;
import org.techlab.labxpert.entity.Analyse;
import org.techlab.labxpert.entity.Planification;
import org.techlab.labxpert.repository.AnalyseRepository;
import org.techlab.labxpert.repository.PlanificationRepository;
import org.techlab.labxpert.service.I_Analyse;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalyseServiceImp implements I_Analyse {
    @Autowired
    AnalyseRepository analyseRepository;
    @Autowired
    PlanificationRepository planificationRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public AnalyseDTO addAnalyse(AnalyseDTO analyseDTO) {
        Analyse analyse=analyseRepository.save(modelMapper.map(analyseDTO,Analyse.class));
        return modelMapper.map(analyse, AnalyseDTO.class);
    }

    @Override
    public AnalyseDTO modAnalyse(AnalyseDTO analyseDTO) {
        Analyse analyse=analyseRepository.save(modelMapper.map(analyseDTO,Analyse.class));
        return modelMapper.map(analyse, AnalyseDTO.class);
    }

    @Override
    public boolean delAnalyse(AnalyseDTO analyseDTO) {
        analyseDTO.setDeleted(Boolean.TRUE);
        Analyse analyse=analyseRepository.save(modelMapper.map(analyseDTO,Analyse.class));
        return analyse.isDeleted();
    }

    @Override
    public List<AnalyseDTO> showAnalyses() {
        List<Analyse> analyses=analyseRepository.findByDeletedFalse();
        List<AnalyseDTO> analysesDTO=analyses.stream().map(a->modelMapper.map(a, AnalyseDTO.class)).collect(Collectors.toList());
        return analysesDTO;
    }
    @Override
    public AnalyseDTO showAnalyseWithId(Long id) {
        Analyse analyse=analyseRepository.findById(id).get();
        AnalyseDTO analyseDTO=modelMapper.map(analyse, AnalyseDTO.class);
        return analyseDTO;
    }

    @Override
    public PlanificationDTO planifierAnalyse(PlanificationDTO planificationDTO) {
        Planification planification=planificationRepository.save(modelMapper.map(planificationDTO,Planification.class));
        return modelMapper.map(planification, PlanificationDTO.class);
    }

    @Override
    public List<Object[]> printResultRepport(Long id) {
        return analyseRepository.printAnalyseRepport(id);
    }
}
