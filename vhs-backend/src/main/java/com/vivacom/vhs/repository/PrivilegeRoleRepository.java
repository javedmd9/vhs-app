package com.vivacom.vhs.repository;

import com.vivacom.vhs.model.PrivilegeRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PrivilegeRoleRepository extends JpaRepository<PrivilegeRoles, Integer> {
    List<PrivilegeRoles> findByRoleId(Integer roleId);

    @Transactional
    @Modifying
    void deleteByRoleId(Integer roleId);
}
