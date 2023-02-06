package com.vivacom.vhs.controller;

import com.vivacom.vhs.exception.ResourceNotFoundException;
import com.vivacom.vhs.model.LabAppointment;
import com.vivacom.vhs.repository.LabAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labAppointment")
public class LabAppointmentController {

    @Autowired
    public LabAppointmentRepository labAppointmentRepository;

    @GetMapping("/find")
    public List<LabAppointment> getAllLabAppointments(){
        return labAppointmentRepository.findAll();
    }

    @PostMapping("/add")
    public LabAppointment createAppointment(@RequestBody LabAppointment labAppointment){
        return labAppointmentRepository.save(labAppointment);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<LabAppointment> getlabAppointmentByid(@RequestParam int id){
        LabAppointment labAppointment=labAppointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment is not exist with id:" + id));
        return ResponseEntity.ok(labAppointment);
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<LabAppointment> updateLabAppointment(@RequestParam int id, @RequestBody LabAppointment labAppDtls){
        LabAppointment updatelabApp = labAppointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not exist with id:" + id));
        updatelabApp.setType(labAppDtls.getType());
        updatelabApp.setLabid(labAppDtls.getLabid());
        updatelabApp.setPatientid(labAppDtls.getPatientid());
        updatelabApp.setVisitingtime(labAppDtls.getVisitingtime());
        updatelabApp.setStatus(labAppDtls.getStatus());
        updatelabApp.setCreatedDate(labAppDtls.getCreatedDate());
        updatelabApp.setModifiedDate(labAppDtls.getModifiedDate());


        labAppointmentRepository.save(updatelabApp);

        return ResponseEntity.ok(updatelabApp);
    }
}
