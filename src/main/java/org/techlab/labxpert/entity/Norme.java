package org.techlab.labxpert.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name="norme")
public class Norme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNorme;
    private String libelle;
    private  String unite;
    private double maxValue;
    private double minValue;
    @Column(name="is_deleted")
    private boolean deleted;


}
