package com.vivacom.vhs.repository;

import com.vivacom.vhs.model.LabAppointmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabAppointmentHistoryRepository extends JpaRepository<LabAppointmentHistory, Integer> {
}
