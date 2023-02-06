package com.vivacom.vhs.controller;

import com.vivacom.vhs.exception.ResourceNotFoundException;
import com.vivacom.vhs.model.Patient;
import com.vivacom.vhs.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/find")
    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    @PostMapping("/add")
    public Patient createPatient(@RequestBody Patient patient){
        return patientRepository.save(patient);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Patient> getPatientByid(@RequestParam int id){
        Patient patient =patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id:" + id));
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<Patient> updatePatient(@RequestParam int id,@RequestBody Patient patientDetails){
        Patient updatePatient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id:" + id));
        updatePatient.setName(patientDetails.getName());
        updatePatient.setAddress(patientDetails.getAddress());
        updatePatient.setEmail(patientDetails.getEmail());
        updatePatient.setMobile(patientDetails.getMobile());
        updatePatient.setAlternatemobile(patientDetails.getAlternatemobile());
        updatePatient.setCity(patientDetails.getCity());
        updatePatient.setCountry(patientDetails.getCountry());
        updatePatient.setState(patientDetails.getState());
        updatePatient.setPincode(patientDetails.getPincode());
        updatePatient.setNationaltype(patientDetails.getNationaltype());
        updatePatient.setNationalid(patientDetails.getNationalid());
        updatePatient.setCreatedDate(patientDetails.getCreatedDate());
        updatePatient.setModifiedDate(patientDetails.getCreatedDate());
        updatePatient.setHealthRecordsid(patientDetails.getHealthRecordsid());

        patientRepository.save(updatePatient);

        return ResponseEntity.ok(updatePatient);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deletePatient(@RequestParam int id){

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id:" + id));

        patientRepository.delete(patient);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}






