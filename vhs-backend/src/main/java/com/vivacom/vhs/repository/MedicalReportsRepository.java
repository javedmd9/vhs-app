package com.vivacom.vhs.repository;

import com.vivacom.vhs.model.MedicalReports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalReportsRepository extends JpaRepository<MedicalReports, Integer> {
}
