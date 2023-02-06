package com.vivacom.vhs.controller;

import com.vivacom.vhs.exception.ResourceNotFoundException;
import com.vivacom.vhs.model.HealthRecords;
import com.vivacom.vhs.repository.HealthRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/healthRecords")
public class HealthRecordsController {

    @Autowired
    public HealthRecordsRepository healthRecordsRepository;

    @GetMapping("/find")
    public List<HealthRecords> getAllHealthRecords(){
        return healthRecordsRepository.findAll();
    }

    @PostMapping("/add")
    public HealthRecords createHealthRecords(@RequestBody HealthRecords healthRecords){
        return healthRecordsRepository.save(healthRecords);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<HealthRecords> getHealthRecordsByid(@RequestParam int id){
        HealthRecords healthRecords =healthRecordsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Healthrecords not exist with id:" + id));
        return ResponseEntity.ok(healthRecords);
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<HealthRecords> updateHealthRecord(@RequestParam int id,@RequestBody HealthRecords healthRecordDtls){
        HealthRecords updateHealthRecords = healthRecordsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Healthrecords not exist with id:" + id));
        updateHealthRecords.setName(healthRecordDtls.getName());
        updateHealthRecords.setMobile(healthRecordDtls.getMobile());
        updateHealthRecords.setExpirydate(healthRecordDtls.getExpirydate());
        updateHealthRecords.setCreatedDate(healthRecordDtls.getCreatedDate());
        updateHealthRecords.setModifiedDate(healthRecordDtls.getModifiedDate());

        healthRecordsRepository.save(updateHealthRecords);

        return ResponseEntity.ok(updateHealthRecords);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deletehealthRecord(@RequestParam int id){

        HealthRecords healthRecords= healthRecordsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id:" + id));

        healthRecordsRepository.delete(healthRecords);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
