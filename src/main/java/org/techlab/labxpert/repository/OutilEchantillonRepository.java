package org.techlab.labxpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.techlab.labxpert.entity.OutilEchantillon;

@Repository
public interface OutilEchantillonRepository extends JpaRepository<OutilEchantillon,Long> {
}
