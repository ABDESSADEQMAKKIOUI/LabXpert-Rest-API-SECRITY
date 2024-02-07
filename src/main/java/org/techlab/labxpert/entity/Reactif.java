package org.techlab.labxpert.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name="reactifs")
public class Reactif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReactif;
    private String nom;
    private String description;
    private int quantite;
    private Date dateExpiration;
    @ManyToOne
    private Fournisseur fournisseur;
    @Column(name="is_deleted")
    private boolean deleted;

    public Reactif() {
    }
}
