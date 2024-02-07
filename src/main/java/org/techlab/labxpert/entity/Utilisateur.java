package org.techlab.labxpert.entity;


import lombok.Data;
import org.techlab.labxpert.Enum.RoleUser;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="utilisateurs")
public class Utilisateur extends Personne{
    private RoleUser role;
    private String password;
    private String nomUtilisateur;


    public Utilisateur() {
        super();
    }

}
