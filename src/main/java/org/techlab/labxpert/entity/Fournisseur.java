package org.techlab.labxpert.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
@Data
@AllArgsConstructor
@Entity

@Table(name="fournisseurs")
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFournisseur;
    private String nom;
    private String adresse;
    private String tel;
    @Column(name="is_deleted")
    private boolean deleted;

    public Fournisseur() {
    }


}
