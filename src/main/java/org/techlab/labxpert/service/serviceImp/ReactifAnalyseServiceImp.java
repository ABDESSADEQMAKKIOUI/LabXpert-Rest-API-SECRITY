package org.techlab.labxpert.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.techlab.labxpert.entity.ReactifAnalyse;

import org.techlab.labxpert.repository.ReactifAnalyseRepository;
import org.techlab.labxpert.service.I_ReactifAnalyse;


    @Service
    public class ReactifAnalyseServiceImp implements I_ReactifAnalyse {
        @Autowired
       ReactifAnalyseRepository reactifAnalyseRepository;
        @Override
        public ReactifAnalyse add(ReactifAnalyse reactifAnalyse) {

            return reactifAnalyseRepository.save(reactifAnalyse);
        }
    }

