package com.vivacom.vhs.repository;

import com.vivacom.vhs.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package, Integer> {
    Package findByName(String name);

    List<Package> findByIdIn(List<Integer> packages);

}
