package org.techlab.labxpert.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;
import org.techlab.labxpert.Enum.StatutEchantillon;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name="echantillons")
public class Echantillon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEchantillon;
    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Patient patient;
    @ManyToOne
    private Utilisateur utilisateur;
    private Date datePrelevement;
    private String typeAnalyse;
    private StatutEchantillon Status;
    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "echantillon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Analyse> analyses;
    @Column(name="is_deleted")
    private boolean deleted;

    public Echantillon() {
    }


}
