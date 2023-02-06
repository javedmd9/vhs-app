package com.vivacom.vhs.repository;

import com.vivacom.vhs.model.LabAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabAppointmentRepository extends JpaRepository<LabAppointment, Integer> {
}
