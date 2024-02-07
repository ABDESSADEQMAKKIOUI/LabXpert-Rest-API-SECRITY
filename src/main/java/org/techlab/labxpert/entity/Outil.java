package org.techlab.labxpert.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="outils")
public class Outil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOutil;
    private String libelle;
    private int quantite;
    @ManyToOne
    private Fournisseur fournisseur;
    @Column(name="is_deleted")
    private boolean deleted;
}
