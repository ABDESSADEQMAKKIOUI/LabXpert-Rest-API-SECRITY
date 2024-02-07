package org.techlab.labxpert.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="outilsechantillon")
public class OutilEchantillon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Echantillon echantillon;
    @ManyToOne
    private Outil outil;
    private int quantite;
}
