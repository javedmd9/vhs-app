package com.vivacom.vhs.repository;

import com.vivacom.vhs.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Roles findByName(String roleName);

    List<Roles> findByNameContaining(String roleName);
}
