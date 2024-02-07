package org.techlab.labxpert.service;


import org.techlab.labxpert.dtos.ReactifDTO;
import org.techlab.labxpert.entity.Reactif;

import java.util.List;

public interface I_Reactif {
    public ReactifDTO addReactif(ReactifDTO reactifdto);
    public ReactifDTO modReactif(ReactifDTO reactifdto);
    public boolean delReactif(ReactifDTO reactifdto);

    public List<ReactifDTO>showReactif();
    public ReactifDTO showReactifwithid(Long id);
}
