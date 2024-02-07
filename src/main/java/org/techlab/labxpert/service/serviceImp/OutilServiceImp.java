package org.techlab.labxpert.service.serviceImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techlab.labxpert.dtos.OutilDTO;
import org.techlab.labxpert.entity.Outil;
import org.techlab.labxpert.repository.OutilRepository;
import org.techlab.labxpert.service.I_Outil;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class OutilServiceImp implements I_Outil {

    @Autowired
    OutilRepository outilRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public OutilDTO addOutil(OutilDTO outilDTO) {
        Outil outil=outilRepository.save(modelMapper.map(outilDTO,Outil.class));
        return modelMapper.map(outil,OutilDTO.class);
    }

    @Override
    public OutilDTO modOutil(OutilDTO outilDTO) {
        Outil outil=outilRepository.save(modelMapper.map(outilDTO,Outil.class));
        return modelMapper.map(outil,OutilDTO.class);
    }

    @Override
    public OutilDTO outilById(Long id) {
        Outil outil=outilRepository.findById(id).get();
        return modelMapper.map(outil,OutilDTO.class);
    }

    @Override
    public boolean delOutil(OutilDTO outilDTO) {
        outilDTO.setDeleted(Boolean.TRUE);
        Outil outil=outilRepository.save(modelMapper.map(outilDTO,Outil.class));
        return outil.isDeleted();
    }

    @Override
    public List<OutilDTO> allOutils() {
        List<Outil> outils=outilRepository.findByDeletedFalse();
        return outils.stream().map(o->modelMapper.map(o,OutilDTO.class)).collect(Collectors.toList());
    }
}
