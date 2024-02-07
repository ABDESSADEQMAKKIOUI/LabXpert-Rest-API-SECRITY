package org.techlab.labxpert.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="reactifsanalyses")
public class ReactifAnalyse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Analyse analyse;
    @ManyToOne
    private Reactif reactif;
    private int quantite;

}
