package com.vivacom.vhs.controller;

import com.vivacom.vhs.dto.PackageDto;
import com.vivacom.vhs.model.LabTests;
import com.vivacom.vhs.model.Package;
import com.vivacom.vhs.repository.LabTestRepository;
import com.vivacom.vhs.repository.PackageRepository;
import com.vivacom.vhs.constants.Status;
import com.vivacom.vhs.utils.StatusDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/packages")
@Slf4j
public class PackageController {

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private LabTestRepository labTestRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public StatusDto createNewPackage(@RequestBody PackageDto packageRequest){
        log.info("Entering createNewPackage");
        StatusDto statusDto = new StatusDto();
        try{
            Package existingPackage = packageRepository.findByName(packageRequest.getName());
            if (existingPackage != null && packageRequest.getId() == null){
                return StatusDto.builder().result(Status.FAILED).message("Package with this name already present.").build();
            }
            List<LabTests> labTests;
            Package packages = convertPackageDtoToPackage(packageRequest);
            if (packageRequest.getLabTestList() != null){
                labTests = labTestRepository.findByIdIn(packageRequest.getLabTestList());
                packages.setLabTestses(labTests);
            }
            packages.setCreatedAt(LocalDate.now());
            packageRepository.save(packages);
            statusDto.setResult(Status.SUCCESS);
            statusDto.setMessage("Record saved.");
        } catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            statusDto.setResult(Status.FAILED);
            statusDto.setMessage("Some exception occurred");
        }
        log.info("Exiting createNewPackage");
        return statusDto;
    }

    @RequestMapping(value = "/view-all", method = RequestMethod.GET)
    public List<Package> viewAllPackages(){
        return packageRepository.findAll();
    }

    @RequestMapping(value = "/find-package-by-name", method = RequestMethod.POST)
    public Package findPackageByName(@RequestBody PackageDto packageDto){
        log.info("Entering :: findPackageByName");
        Package aPackage = packageRepository.findByName(packageDto.getName());
        log.info("Exiting :: findPackageByName");
        return aPackage;
    }

    private Package convertPackageDtoToPackage(PackageDto dto){
        Package packages = new Package();
        if (dto.getId() != null){
            packages.setId(dto.getId());
        }
        packages.setName(dto.getName());
        packages.setDescription(dto.getDescription());
        packages.setAmount(dto.getAmount());
        packages.setDiscount(dto.getDiscount());
        packages.setCategory(dto.getCategory());
        packages.setType(dto.getType());
        packages.setPreCaution(dto.getPreCaution());
        packages.setReportDuration(dto.getReportDuration());
        packages.setHandlingCharge(dto.getHandlingCharge());
        return packages;
    }
}
