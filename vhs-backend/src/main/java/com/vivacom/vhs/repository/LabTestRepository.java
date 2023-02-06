package com.vivacom.vhs.repository;

import com.vivacom.vhs.model.Lab;
import com.vivacom.vhs.model.LabTests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface LabTestRepository extends JpaRepository<LabTests, Integer> {
    LabTests findByTestName(String testName);

    List<LabTests> findByIdIn(List<Integer> labTestList);

    List<LabTests> findByLab(Lab lab);
}
