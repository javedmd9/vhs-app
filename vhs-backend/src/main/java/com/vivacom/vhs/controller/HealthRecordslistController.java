package com.vivacom.vhs.controller;

import com.vivacom.vhs.exception.ResourceNotFoundException;
import com.vivacom.vhs.model.HealthRecords;
import com.vivacom.vhs.model.HealthRecordslist;
import com.vivacom.vhs.repository.HealthRecordslistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/healthRecordslist")
public class HealthRecordslistController {

    @Autowired
    public HealthRecordslistRepository healthRecordslistRepository;

    @GetMapping("/find")
    public List<HealthRecordslist> getAllHealthRecordslist(){
        return healthRecordslistRepository.findAll();
    }

    @PostMapping("/add")
    public HealthRecordslist createHealthRecordslist(@RequestBody HealthRecordslist healthRecordslist){
        return healthRecordslistRepository.save(healthRecordslist);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<HealthRecordslist> getHealthRecordslistByid(@RequestParam int id){
        HealthRecordslist healthRecordslist =healthRecordslistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Healthrecordslist is not exist with id:" + id));
        return ResponseEntity.ok(healthRecordslist);
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<HealthRecordslist> updateHealthRecordlist(@RequestParam int id,@RequestBody HealthRecordslist healthRecordlistDtls){
        HealthRecordslist updateHealthRecordslist = healthRecordslistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Healthrecordslist is not exist with id:" + id));
        updateHealthRecordslist.setCurrname(healthRecordlistDtls.getCurrname());
        updateHealthRecordslist.setPrevname(healthRecordlistDtls.getPrevname());
        updateHealthRecordslist.setCurrmobile(healthRecordlistDtls.getCurrmobile());
        updateHealthRecordslist.setPrevmobile(healthRecordlistDtls.getPrevmobile());
        updateHealthRecordslist.setCurrexpirydate(healthRecordlistDtls.getCurrexpirydate());
        updateHealthRecordslist.setPrevexpirydate(healthRecordlistDtls.getPrevexpirydate());
        updateHealthRecordslist.setLastmodifiedDate(healthRecordlistDtls.getLastmodifiedDate());
        updateHealthRecordslist.setCurrmodifiedDate(healthRecordlistDtls.getCurrmodifiedDate());
        updateHealthRecordslist.setCreatedDate(healthRecordlistDtls.getCreatedDate());

        healthRecordslistRepository.save(updateHealthRecordslist);

        return ResponseEntity.ok(updateHealthRecordslist);
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deletehealthRecordlist(@RequestParam int id){

        HealthRecordslist healthRecordslist= healthRecordslistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Healthrecordlist is not exist with id:" + id));

        healthRecordslistRepository.delete(healthRecordslist);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
