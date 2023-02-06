package com.vivacom.vhs.controller;

import com.vivacom.vhs.exception.ResourceNotFoundException;
import com.vivacom.vhs.model.Doctor;
import com.vivacom.vhs.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    public DoctorRepository doctorRepository;

    @GetMapping("/find")
    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    @PostMapping("/add")
    public Doctor createDoctor(@RequestBody Doctor doctor){
        return doctorRepository.save(doctor);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Doctor> getdoctorByid(@RequestParam int id){
        Doctor doctor =doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor is not exist with id:" + id));
        return ResponseEntity.ok(doctor);
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<Doctor> updateDoctor(@RequestParam int id,@RequestBody Doctor doctorDtls){
        Doctor updateDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not exist with id:" + id));
        updateDoctor.setName(doctorDtls.getName());
        updateDoctor.setEmail(doctorDtls.getEmail());
        updateDoctor.setMobile(doctorDtls.getMobile());
        updateDoctor.setAlternatemobile(doctorDtls.getAlternatemobile());
        updateDoctor.setSpecialization(doctorDtls.getSpecialization());
        updateDoctor.setAddress(doctorDtls.getAddress());
        updateDoctor.setCity(doctorDtls.getCity());
        updateDoctor.setCountry(doctorDtls.getCountry());
        updateDoctor.setState(doctorDtls.getState());
        updateDoctor.setPincode(doctorDtls.getPincode());
        updateDoctor.setStatus(doctorDtls.getStatus());
        updateDoctor.setCertificate1(doctorDtls.getCertificate1());
        updateDoctor.setCertificate2(doctorDtls.getCertificate2());
        updateDoctor.setCertificate3(doctorDtls.getCertificate3());
        updateDoctor.setCreatedDate(doctorDtls.getCreatedDate());
        updateDoctor.setModifiedDate(doctorDtls.getModifiedDate());

        doctorRepository.save(updateDoctor);

        return ResponseEntity.ok(updateDoctor);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deleteDoctor(@RequestParam int id){

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not exist with id:" + id));

        doctorRepository.delete(doctor);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
