package org.techlab.labxpert.service.serviceImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techlab.labxpert.dtos.NormeDTO;
import org.techlab.labxpert.entity.Norme;
import org.techlab.labxpert.repository.NormeRepository;
import org.techlab.labxpert.service.I_Norme;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NormeServiceImp implements I_Norme {

    @Autowired
    NormeRepository normeRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public NormeDTO addNorme(NormeDTO normeDTO) {
        Norme norme=normeRepository.save(modelMapper.map(normeDTO,Norme.class));
        return modelMapper.map(norme, NormeDTO.class);
    }

    @Override
    public NormeDTO getNormeById(Long id) {
        return modelMapper.map(normeRepository.findById(id).get(), NormeDTO.class);
    }

    @Override
    public NormeDTO modNorme(NormeDTO normeDTO) {
        Norme norme=normeRepository.save(modelMapper.map(normeDTO,Norme.class));
        return modelMapper.map(norme, NormeDTO.class);
    }

    @Override
    public boolean delNorme(NormeDTO normeDTO) {
        normeDTO.setDeleted(Boolean.TRUE);
        Norme norme=normeRepository.save(modelMapper.map(normeDTO,Norme.class));
        return modelMapper.map(norme, NormeDTO.class).isDeleted();
    }

    @Override
    public List<NormeDTO> showNormes() {
        List<Norme> normes=normeRepository.findByDeletedFalse();
        return normes.stream().map(n->modelMapper.map(n, NormeDTO.class)).collect(Collectors.toList());
    }
}
