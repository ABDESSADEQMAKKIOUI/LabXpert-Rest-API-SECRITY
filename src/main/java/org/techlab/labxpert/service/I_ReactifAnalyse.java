package org.techlab.labxpert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.techlab.labxpert.entity.OutilEchantillon;
import org.techlab.labxpert.entity.ReactifAnalyse;
import org.techlab.labxpert.repository.OutilEchantillonRepository;
import org.techlab.labxpert.repository.ReactifAnalyseRepository;

public interface I_ReactifAnalyse {

    public ReactifAnalyse add(ReactifAnalyse reactifAnalyse);

}
