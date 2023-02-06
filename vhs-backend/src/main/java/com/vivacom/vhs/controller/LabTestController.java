package com.vivacom.vhs.controller;

import com.vivacom.vhs.dto.LabTestDto;
import com.vivacom.vhs.model.Lab;
import com.vivacom.vhs.model.LabTests;
import com.vivacom.vhs.repository.LabRepository;
import com.vivacom.vhs.repository.LabTestRepository;
import com.vivacom.vhs.constants.Status;
import com.vivacom.vhs.utils.StatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/lab-test")
public class LabTestController {

    @Autowired
    private LabTestRepository labTestRepository;

    @Autowired
    private LabRepository labRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public StatusDto createNewLabTest(@RequestBody LabTestDto labTests){
        StatusDto statusDto = new StatusDto();
        try{
            if (labTests.getId() != null){
                return updateLabTest(labTests);
            }
            LabTests existingLabTest = labTestRepository.findByTestName(labTests.getTestName());
            if (existingLabTest != null && labTests.getId() == null){
                return StatusDto.builder().result(Status.FAILED).message("Lab test with this name already exists.").build();
            }
            LabTests newLabTest = convertLabTestDtoToLabTest(labTests);
            newLabTest.setCreatedAt(LocalDate.now());
            labTestRepository.save(newLabTest);
            statusDto.setResult(Status.SUCCESS);
            statusDto.setMessage("Successfully created new lab test.");
        } catch (Exception e){
            e.printStackTrace();
            statusDto.setResult(Status.FAILED);
            statusDto.setMessage("Some exception occurred");
        }
        return statusDto;
    }

    private StatusDto updateLabTest(LabTestDto dto){
        labTestRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("id is null or empty"));
        LabTests existingLabTests;
        existingLabTests = convertLabTestDtoToLabTest(dto);
        existingLabTests.setModifiedAt(LocalDate.now());
        labTestRepository.save(existingLabTests);
        return StatusDto.builder().result(Status.SUCCESS).message("Successfully updated the lab").build();
    }

    @RequestMapping(value = "/view-all", method = RequestMethod.GET)
    public List<LabTests> viewAllLabTest(){
        return labTestRepository.findAll();
    }

    @RequestMapping(value = "/find-by-lab", method = RequestMethod.POST)
    public List<LabTests> findByLab(@RequestBody LabTestDto dto){
        Lab lab = labRepository.findById(dto.getLabId()).orElseThrow(() -> new RuntimeException("Lab not found"));
        return labTestRepository.findByLab(lab);
    }

    private LabTests convertLabTestDtoToLabTest(LabTestDto dto){
        LabTests labTests = new LabTests();
        if (dto.getId() != null){
            labTests.setId(dto.getId());
        }
        labTests.setTestName(dto.getTestName());
        labTests.setTestDesc(dto.getTestDesc());
        labTests.setTestPrefix(dto.getTestPrefix());
        labTests.setPreCaution(dto.getPreCaution());
        labTests.setTestPrice(dto.getTestPrice());
        labTests.setDiscountRate(dto.getDiscountRate());
        labTests.setHandlingCharge(dto.getHandlingCharge());
        labTests.setSampleType(dto.getSampleType());
        labTests.setTestReportDuration(dto.getTestReportDuration());
        Optional<Lab> lab = labRepository.findById(dto.getLabId());
        lab.ifPresent(labTests::setLab);
        return labTests;
    }
}
