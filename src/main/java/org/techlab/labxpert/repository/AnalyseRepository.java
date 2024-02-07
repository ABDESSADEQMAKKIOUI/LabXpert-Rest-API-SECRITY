package org.techlab.labxpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.techlab.labxpert.entity.Analyse;

import java.util.List;

@Repository
public interface AnalyseRepository extends JpaRepository<Analyse,Long> {
    List<Analyse> findByDeletedFalse();

    /*@Query(value = "select analyses.nom_analyse,nom,prenom,tel,adresse,date_naissance,statut,value,libelle,max_value,min_value,unite from analyses,numerations,norme,echantillons,patients where analyses.id_analyse=numerations.analyse_id_analyse and numerations.norme_id_norme=norme.id_norme and analyses.id_analyse=?1 and analyses.echantillon_id_echantillon=echantillons.id_echantillon and echantillons.patient_id=patients.id",nativeQuery = true)
    List printAnalyseRepport(Long id);*/

    @Query("SELECT a.nomAnalyse, p.nom, p.prenom, p.tel, p.adresse, p.dateNaissance, n.statut, n.value, norm.libelle, norm.maxValue, norm.minValue, norm.unite " +
            "FROM Analyse a " +
            "JOIN Numeration n ON a = n.analyse " +
            "JOIN Norme norm ON n.norme = norm " +
            "JOIN Echantillon s ON a.echantillon = s " +
            "JOIN Patient p ON s.patient = p " +
            "WHERE a.idAnalyse = ?1")
    List<Object[]>  printAnalyseRepport(Long id);
}
