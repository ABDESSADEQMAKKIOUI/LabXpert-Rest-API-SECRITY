package org.techlab.labxpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.techlab.labxpert.entity.Analyse;
import org.techlab.labxpert.entity.Numeration;

import java.util.List;

@Repository
public interface NumerationRepository extends JpaRepository<Numeration,Long> {
    public List<Numeration> findByAnalyse(Analyse analyse);
}
