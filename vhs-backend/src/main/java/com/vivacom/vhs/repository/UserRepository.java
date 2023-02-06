package com.vivacom.vhs.repository;

import com.vivacom.vhs.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUserName(String username);
}
