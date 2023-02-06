package com.vivacom.vhs.controller;


import com.vivacom.vhs.exception.ResourceNotFoundException;
import com.vivacom.vhs.model.Appointment;
import com.vivacom.vhs.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    public AppointmentRepository appointmentRepository;

    @GetMapping("/find")
    public List<Appointment> getAllappointments(){
        return appointmentRepository.findAll();
    }

    @PostMapping("/add")
    public Appointment createAppointment(@RequestBody Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Appointment> getAppointmentByid(@RequestParam int id){
        Appointment appointment=appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment is not exist with id:" + id));
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<Appointment> updateAppointment(@RequestParam int id, @RequestBody Appointment appointmentDtls){
        Appointment updateAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not exist with id:" + id));
        updateAppointment.setType(appointmentDtls.getType());
        updateAppointment.setPatientid(appointmentDtls.getPatientid());
        updateAppointment.setDoctorid(appointmentDtls.getDoctorid());
        updateAppointment.setVisitingtime(appointmentDtls.getVisitingtime());
        updateAppointment.setStatus(appointmentDtls.getStatus());
        updateAppointment.setCreatedDate(appointmentDtls.getCreatedDate());
        updateAppointment.setModifiedDate(appointmentDtls.getModifiedDate());


        appointmentRepository.save(updateAppointment);

        return ResponseEntity.ok(updateAppointment);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deleteAppointment(@RequestParam int id){

        Appointment appointment= appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not exist with id:" + id));

        appointmentRepository.delete(appointment);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
