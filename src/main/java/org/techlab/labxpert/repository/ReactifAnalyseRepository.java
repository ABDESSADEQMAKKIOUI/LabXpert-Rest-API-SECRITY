package org.techlab.labxpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.techlab.labxpert.entity.ReactifAnalyse;

@Repository
public interface ReactifAnalyseRepository extends JpaRepository<ReactifAnalyse,Long> {
}
