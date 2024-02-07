package org.techlab.labxpert.service;


import org.techlab.labxpert.dtos.NormeDTO;
import org.techlab.labxpert.entity.Norme;

import java.util.List;

public interface I_Norme {
    public NormeDTO addNorme(NormeDTO normeDTO);
    public NormeDTO getNormeById(Long id);

    public NormeDTO modNorme(NormeDTO normeDTO);
    public boolean delNorme(NormeDTO normeDTO);
    public List<NormeDTO> showNormes();
}
