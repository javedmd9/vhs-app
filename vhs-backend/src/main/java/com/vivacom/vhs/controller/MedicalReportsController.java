package com.vivacom.vhs.controller;

import com.vivacom.vhs.exception.ResourceNotFoundException;
import com.vivacom.vhs.model.MedicalReports;
import com.vivacom.vhs.repository.MedicalReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicalreports")
public class MedicalReportsController {

    @Autowired
    public MedicalReportsRepository medicalReportsRepository;


    @GetMapping("/find")
    public List<MedicalReports> getAllmedicalReports(){
        return medicalReportsRepository.findAll();
    }

    @PostMapping("/add")
    public MedicalReports createMedicalReports(@RequestBody MedicalReports medicalReports){
        return medicalReportsRepository.save(medicalReports);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<MedicalReports> getMedicalReportsByid(@RequestParam int id){
        MedicalReports medicalReports=medicalReportsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalReport is not exist with id:" + id));
        return ResponseEntity.ok(medicalReports);
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<MedicalReports> updateMedicalReports(@RequestParam int id, @RequestBody MedicalReports medicalReports){
        MedicalReports updateMedicalReports = medicalReportsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalReport not exist with id:" + id));
        updateMedicalReports.setType(medicalReports.getType());
        updateMedicalReports.setPatientid(medicalReports.getPatientid());
        updateMedicalReports.setReferredby(medicalReports.getReferredby());
        updateMedicalReports.setPreparedby(medicalReports.getPreparedby());
        updateMedicalReports.setReportfile(medicalReports.getReportfile());
        updateMedicalReports.setCreatedDate(medicalReports.getCreatedDate());
        updateMedicalReports.setModifiedDate(medicalReports.getModifiedDate());

        medicalReportsRepository.save(updateMedicalReports);

        return ResponseEntity.ok(updateMedicalReports);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deleteMedicalReport(@RequestParam int id){

        MedicalReports medicalReports= medicalReportsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalReport not exist with id:" + id));

        medicalReportsRepository.delete(medicalReports);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
