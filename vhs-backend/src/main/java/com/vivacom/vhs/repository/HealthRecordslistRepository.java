package com.vivacom.vhs.repository;

import com.vivacom.vhs.model.HealthRecordslist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRecordslistRepository extends JpaRepository<HealthRecordslist, Integer> {
}
