package com.vivacom.vhs.controller;


import com.vivacom.vhs.exception.ResourceNotFoundException;
import com.vivacom.vhs.model.Pharmacy;
import com.vivacom.vhs.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacy")
public class PharmacyController {

    @Autowired
    public PharmacyRepository pharmacyRepository;

    @GetMapping("/find")
    public List<Pharmacy> getAllPharmacies(){
        return pharmacyRepository.findAll();
    }

    @PostMapping("/add")
    public Pharmacy createPharmacy(@RequestBody Pharmacy pharmacy){
        return pharmacyRepository.save(pharmacy);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Pharmacy> getPharmacyByid(@RequestParam int id){
        Pharmacy pharmacy=pharmacyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pharmacy is not exist with id:" + id));
        return ResponseEntity.ok(pharmacy);
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<Pharmacy> updatePharmacy(@RequestParam int id, @RequestBody Pharmacy pharmacy){
        Pharmacy updatePharmacy = pharmacyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pharmacy not exist with id:" + id));
        updatePharmacy.setEmail(pharmacy.getEmail());
        updatePharmacy.setMobile(pharmacy.getMobile());
        updatePharmacy.setAlternatemobile(pharmacy.getAlternatemobile());
        updatePharmacy.setAddress(pharmacy.getAddress());
        updatePharmacy.setCreatedDate(pharmacy.getCreatedDate());
        updatePharmacy.setModifiedDate(pharmacy.getModifiedDate());

        pharmacyRepository.save(updatePharmacy);

        return ResponseEntity.ok(updatePharmacy);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deletePharmacy(@RequestParam int id){

        Pharmacy pharmacy= pharmacyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pharmacy not exist with id:" + id));

        pharmacyRepository.delete(pharmacy);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
