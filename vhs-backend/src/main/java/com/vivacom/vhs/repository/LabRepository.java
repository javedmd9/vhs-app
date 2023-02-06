package com.vivacom.vhs.repository;

import com.vivacom.vhs.model.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabRepository extends JpaRepository<Lab, Integer> {
}
