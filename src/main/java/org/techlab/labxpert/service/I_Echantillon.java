package org.techlab.labxpert.service;


import org.techlab.labxpert.dtos.EchantillonDTO;
import org.techlab.labxpert.entity.Echantillon;

import java.util.List;

public interface I_Echantillon {
    public List<EchantillonDTO> showEhantillon();
    public EchantillonDTO addEchantillon(EchantillonDTO echantillondto);
    public EchantillonDTO modEchantillon(EchantillonDTO echantillondto);
    public boolean delEchantillhon(EchantillonDTO echantillondto);
    public EchantillonDTO showEchantillonwithid(Long id);

}
