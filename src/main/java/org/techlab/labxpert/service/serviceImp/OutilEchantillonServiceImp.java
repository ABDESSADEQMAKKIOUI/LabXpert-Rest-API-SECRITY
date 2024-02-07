package org.techlab.labxpert.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techlab.labxpert.entity.Outil;
import org.techlab.labxpert.entity.OutilEchantillon;
import org.techlab.labxpert.repository.OutilEchantillonRepository;
import org.techlab.labxpert.service.I_Outil_Echantillon;

@Service
public class OutilEchantillonServiceImp implements I_Outil_Echantillon {
    @Autowired
    OutilEchantillonRepository outilEchantillonRepository;
    @Override
    public OutilEchantillon add(OutilEchantillon outilEchantillon) {
        return outilEchantillonRepository.save(outilEchantillon);
    }
}
