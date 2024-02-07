package org.techlab.labxpert.service.serviceImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techlab.labxpert.dtos.ReactifDTO;

import org.techlab.labxpert.entity.Reactif;

import org.techlab.labxpert.repository.ReactifRepository;
import org.techlab.labxpert.service.I_Reactif;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class ReactifServiceImp implements I_Reactif {
    @Autowired
    ReactifRepository reactifRepository;
    ModelMapper modelMapper=new ModelMapper();
    @Override
    public ReactifDTO addReactif(ReactifDTO reactifdto) {

        Reactif reactif=reactifRepository.save(modelMapper.map(reactifdto,Reactif.class));
        return modelMapper.map(reactif, ReactifDTO.class);
    }


    @Override
    public ReactifDTO modReactif(ReactifDTO reactifdto) {
                Reactif reactif= reactifRepository.save(modelMapper.map(reactifdto,Reactif.class));
        return modelMapper.map(reactif,ReactifDTO.class);
    }
    @Override
    public List<ReactifDTO>showReactif(){
        List<Reactif> reactifs=reactifRepository.findByDeletedFalse();
        return reactifs.stream().map(reactif -> modelMapper.map(reactif,ReactifDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ReactifDTO showReactifwithid(Long id) {
        Reactif reactif=reactifRepository.findById(id).get();
        return modelMapper.map(reactif,ReactifDTO.class);
    }
    @Override
    public boolean delReactif(ReactifDTO reactifdto) {

        reactifdto.setDeleted(Boolean.TRUE);

        Reactif reactif=reactifRepository.save(modelMapper.map(reactifdto,Reactif.class));
        return reactif.isDeleted();
    }




}
