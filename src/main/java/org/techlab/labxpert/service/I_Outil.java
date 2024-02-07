package org.techlab.labxpert.service;

import org.techlab.labxpert.dtos.OutilDTO;

import java.util.List;

public interface I_Outil {

    public OutilDTO addOutil(OutilDTO outilDTO);
    public OutilDTO modOutil(OutilDTO outilDTO);
    public OutilDTO outilById(Long id);
    public boolean delOutil(OutilDTO outilDTO);
    public List<OutilDTO> allOutils();
}
