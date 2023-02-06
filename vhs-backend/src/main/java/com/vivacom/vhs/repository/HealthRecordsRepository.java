package com.vivacom.vhs.repository;

import com.vivacom.vhs.model.HealthRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HealthRecordsRepository extends JpaRepository<HealthRecords, Integer> {
}
