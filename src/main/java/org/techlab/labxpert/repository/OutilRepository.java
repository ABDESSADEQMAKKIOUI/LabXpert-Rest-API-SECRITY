package org.techlab.labxpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.techlab.labxpert.dtos.OutilDTO;
import org.techlab.labxpert.entity.Outil;

import java.util.List;

@Repository
public interface OutilRepository extends JpaRepository<Outil,Long> {

    List<Outil> findByDeletedFalse();
}
