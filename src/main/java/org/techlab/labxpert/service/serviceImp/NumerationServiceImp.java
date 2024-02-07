package org.techlab.labxpert.service.serviceImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.techlab.labxpert.Enum.StatutNumeration;
import org.techlab.labxpert.dtos.AnalyseDTO;
import org.techlab.labxpert.dtos.NumerationDTO;
import org.techlab.labxpert.entity.Analyse;
import org.techlab.labxpert.entity.Norme;
import org.techlab.labxpert.entity.Numeration;
import org.techlab.labxpert.repository.NumerationRepository;
import org.techlab.labxpert.service.I_Numeration;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NumerationServiceImp implements I_Numeration {

    @Autowired
    NumerationRepository numerationRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public NumerationDTO addNumeration(NumerationDTO numerationDTO) {
        Norme norme=numerationDTO.getNorme();
        if (numerationDTO.getValue()>=norme.getMinValue()&& numerationDTO.getValue()<= norme.getMaxValue()){
            numerationDTO.setStatut(StatutNumeration.Normal);
        }
        else {
            numerationDTO.setStatut(StatutNumeration.Anormal);
        }
        Numeration numeration=numerationRepository.save(modelMapper.map(numerationDTO,Numeration.class));
        return modelMapper.map(numeration,NumerationDTO.class);
    }

    @Override
    public NumerationDTO modNumeration(NumerationDTO numerationDTO) {
        Numeration numeration=numerationRepository.save(modelMapper.map(numerationDTO,Numeration.class));
        return modelMapper.map(numeration,NumerationDTO.class);
    }

    @Override
    public NumerationDTO NumerationWithId(Long id) {
        Numeration numeration=numerationRepository.findById(id).get();
        return modelMapper.map(numeration,NumerationDTO.class);
    }

    @Override
    public boolean delNumeration(NumerationDTO numerationDTO) {
        numerationDTO.setDeleted(Boolean.TRUE);
        Numeration numeration=numerationRepository.save(modelMapper.map(numerationDTO,Numeration.class));
        return numeration.isDeleted();
    }

    @Override
    public List<NumerationDTO> allNumerationWithAnalyse(AnalyseDTO analyseDTO) {
        List<Numeration> numerations=numerationRepository.findByAnalyse(modelMapper.map(analyseDTO, Analyse.class));
        List<NumerationDTO> numerationDTOS=numerations.stream().map(n->modelMapper.map(n,NumerationDTO.class)).collect(Collectors.toList());
        return numerationDTOS;
    }

    @Override
    public List<NumerationDTO> allNumeration() {
        List<Numeration> numerations=numerationRepository.findAll();
        List<NumerationDTO> numerationDTOS=numerations.stream().map(n->modelMapper.map(n,NumerationDTO.class)).collect(Collectors.toList());
        return numerationDTOS;
    }
}
