package org.techlab.labxpert.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
@Data
//@ToString(exclude = { "echantillon" })
@Entity
@Table(name="analyses")
public class Analyse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnalyse;
    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Echantillon echantillon;
    private String nomAnalyse;
    private Date dateDebut;
    private Date dateFin;
    private String commantaire;
    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "analyse" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Collection<Numeration> numerations;
    @Column(name="is_deleted")
    private boolean deleted;

    public Analyse() {
    }

}
