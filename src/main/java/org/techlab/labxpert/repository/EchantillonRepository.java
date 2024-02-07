package org.techlab.labxpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.techlab.labxpert.entity.Echantillon;

import java.util.List;

@Repository
public interface EchantillonRepository extends JpaRepository<Echantillon,Long> {
    List<Echantillon> findByDeletedFalse();
}
