package com.vivacom.vhs.repository;

import com.vivacom.vhs.model.AppointmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentHistoryRepository extends JpaRepository<AppointmentHistory, Integer> {
}
