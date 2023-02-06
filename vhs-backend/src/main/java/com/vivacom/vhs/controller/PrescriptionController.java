package com.vivacom.vhs.controller;

import com.vivacom.vhs.exception.ResourceNotFoundException;
import com.vivacom.vhs.model.Prescription;
import com.vivacom.vhs.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {

    @Autowired
    public PrescriptionRepository prescriptionRepository;

    @GetMapping("/find")
    public List<Prescription> getAllPrescriptions(){
        return prescriptionRepository.findAll();
    }

    @PostMapping("/add")
    public Prescription createPrescription(@RequestBody Prescription prescription){
        return prescriptionRepository.save(prescription);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Prescription> getPrescriptionsByid(@RequestParam int id){
        Prescription prescription=prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not exist with id:" + id));
        return ResponseEntity.ok(prescription);
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<Prescription> updatePrescriptions(@RequestParam int id, @RequestBody Prescription prescription){
        Prescription updatePrescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not exist with id:" + id));
        updatePrescription.setFile(prescription.getFile());
        updatePrescription.setCreatedDate(prescription.getCreatedDate());
        updatePrescription.setModifiedDate(prescription.getModifiedDate());

        prescriptionRepository.save(updatePrescription);

        return ResponseEntity.ok(updatePrescription);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deletePrescription(@RequestParam int id){

        Prescription prescription= prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not exist with id:" + id));

        prescriptionRepository.delete(prescription);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
