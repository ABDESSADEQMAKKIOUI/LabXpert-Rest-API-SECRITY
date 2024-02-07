package org.techlab.labxpert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.techlab.labxpert.entity.Planification;

@Repository
public interface PlanificationRepository extends JpaRepository<Planification,Long> {
}
