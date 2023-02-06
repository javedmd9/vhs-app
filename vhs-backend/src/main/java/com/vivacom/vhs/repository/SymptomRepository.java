package com.vivacom.vhs.repository;

import com.vivacom.vhs.model.Symptoms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymptomRepository extends JpaRepository<Symptoms, Integer> {
    Symptoms findByName(String name);
}
