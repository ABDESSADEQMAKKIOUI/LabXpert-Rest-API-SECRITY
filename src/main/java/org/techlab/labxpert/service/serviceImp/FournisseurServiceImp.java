package org.techlab.labxpert.service.serviceImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techlab.labxpert.dtos.FournisseurDTO;
import org.techlab.labxpert.entity.Fournisseur;
import org.techlab.labxpert.repository.FournisseurRepository;
import org.techlab.labxpert.service.I_Fournisseur;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FournisseurServiceImp implements I_Fournisseur {
    @Autowired
    FournisseurRepository fournisseurRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public FournisseurDTO addFournisseur(FournisseurDTO fournisseurDTO) {
        return modelMapper.map(fournisseurRepository.save(modelMapper.map(fournisseurDTO, Fournisseur.class)),FournisseurDTO.class);
    }

    @Override
    public FournisseurDTO getFournisseur(Long id) {
        return modelMapper.map(fournisseurRepository.findById(id),FournisseurDTO.class);
    }

    @Override
    public FournisseurDTO modFournisseur(FournisseurDTO fournisseurDTO) {
        return modelMapper.map(fournisseurRepository.save(modelMapper.map(fournisseurDTO, Fournisseur.class)),FournisseurDTO.class);
    }

    @Override
    public boolean delFournisseur(FournisseurDTO fournisseurDTO) {
        fournisseurDTO.setDeleted(Boolean.TRUE);
        FournisseurDTO fournisseurDTO1=modelMapper.map(fournisseurRepository.save(modelMapper.map(fournisseurDTO, Fournisseur.class)),FournisseurDTO.class);
        return fournisseurDTO1.isDeleted();
    }

    @Override
    public List<FournisseurDTO> showFournisseurs() {
        List<Fournisseur> fournisseurs=fournisseurRepository.findByDeletedFalse();
        return fournisseurs.stream().map(f->modelMapper.map(f,FournisseurDTO.class)).collect(Collectors.toList());
    }
}
