package org.techlab.labxpert.service;


import org.techlab.labxpert.dtos.FournisseurDTO;
import org.techlab.labxpert.entity.Fournisseur;

import java.util.List;

public interface I_Fournisseur {
    public FournisseurDTO addFournisseur(FournisseurDTO fournisseurDTO);
    public FournisseurDTO getFournisseur(Long id);
    public FournisseurDTO modFournisseur(FournisseurDTO fournisseurDTO);
    public boolean delFournisseur(FournisseurDTO fournisseurDTO);
    public List<FournisseurDTO> showFournisseurs();
}
