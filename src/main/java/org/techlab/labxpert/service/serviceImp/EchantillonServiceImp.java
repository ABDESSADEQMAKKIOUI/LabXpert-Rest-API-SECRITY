package org.techlab.labxpert.service.serviceImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techlab.labxpert.dtos.AnalyseDTO;
import org.techlab.labxpert.dtos.EchantillonDTO;
import org.techlab.labxpert.dtos.UtilisateurDTO;
import org.techlab.labxpert.entity.Echantillon;
import org.techlab.labxpert.entity.Utilisateur;
import org.techlab.labxpert.repository.AnalyseRepository;
import org.techlab.labxpert.repository.EchantillonRepository;
import org.techlab.labxpert.service.I_Analyse;
import org.techlab.labxpert.service.I_Echantillon;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EchantillonServiceImp implements I_Echantillon {
    @Autowired
    EchantillonRepository echantillonRepository;
    @Autowired
    I_Analyse i_analyse;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public EchantillonDTO addEchantillon(EchantillonDTO echantillondto) {
        Echantillon echantillon=echantillonRepository.save(modelMapper.map(echantillondto,Echantillon.class));
        return modelMapper.map(echantillon,EchantillonDTO.class);
    }
    @Override
    public List<EchantillonDTO> showEhantillon() {
        List<Echantillon> echantillons=echantillonRepository.findByDeletedFalse();
        return echantillons.stream().map(echantillon->modelMapper.map(echantillon,EchantillonDTO.class)).collect(Collectors.toList());
    }
    @Override
    public EchantillonDTO showEchantillonwithid(Long id) {
        Echantillon echantillon =echantillonRepository.findById(id).get();
        return modelMapper.map(echantillon,EchantillonDTO.class);
    }
    @Override
    public EchantillonDTO modEchantillon(EchantillonDTO echantillondto) {
        Echantillon echantillon=echantillonRepository.save(modelMapper.map(echantillondto,Echantillon.class));
        return modelMapper.map(echantillon,EchantillonDTO.class);
    }
    @Override
    public boolean delEchantillhon(EchantillonDTO echantillondto) {
        echantillondto.setDeleted(true);
        Echantillon echantillon=echantillonRepository.save(modelMapper.map(echantillondto,Echantillon.class));
        return echantillon.isDeleted();
    }
}





