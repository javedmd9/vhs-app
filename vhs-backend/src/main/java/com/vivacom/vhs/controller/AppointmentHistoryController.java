package com.vivacom.vhs.controller;


import com.vivacom.vhs.exception.ResourceNotFoundException;
import com.vivacom.vhs.model.AppointmentHistory;
import com.vivacom.vhs.repository.AppointmentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appHistory")
public class AppointmentHistoryController {

    @Autowired
    public AppointmentHistoryRepository appointmentHistoryRepository;

    @GetMapping("/find")
    public List<AppointmentHistory> getAllAppHistory(){
        return appointmentHistoryRepository.findAll();
    }

    @PostMapping("/add")
    public AppointmentHistory createAppHistory(@RequestBody AppointmentHistory appointmentHistory){
        return appointmentHistoryRepository.save(appointmentHistory);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AppointmentHistory> getAppHistoryByid(@RequestParam int id){
        AppointmentHistory appointmentHistory=appointmentHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AppointmentHistory is not exist with id:" + id));
        return ResponseEntity.ok(appointmentHistory);
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<AppointmentHistory> updateAppHistory(@RequestParam int id, @RequestBody AppointmentHistory appHistoryDtls){
        AppointmentHistory updateAppHistory = appointmentHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AppointmentHistory not exist with id:" + id));
        updateAppHistory.setCurrtype(appHistoryDtls.getCurrtype());
        updateAppHistory.setPrevtype(appHistoryDtls.getPrevtype());
        updateAppHistory.setCurrstatus(appHistoryDtls.getCurrstatus());
        updateAppHistory.setPrevstatus(appHistoryDtls.getPrevstatus());
        updateAppHistory.setPatientid(appHistoryDtls.getPatientid());
        updateAppHistory.setCurrdoctorid(appHistoryDtls.getCurrdoctorid());
        updateAppHistory.setPrevdoctorid(appHistoryDtls.getPrevdoctorid());
        updateAppHistory.setPrevvisitingtime(appHistoryDtls.getPrevvisitingtime());
        updateAppHistory.setCurrvisitingtime(appHistoryDtls.getCurrvisitingtime());
        updateAppHistory.setCreatedDate(appHistoryDtls.getCreatedDate());
        updateAppHistory.setModifiedDate(appHistoryDtls.getModifiedDate());

        appointmentHistoryRepository.save(updateAppHistory);

        return ResponseEntity.ok(updateAppHistory);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deleteAppHistory(@RequestParam int id){

        AppointmentHistory appHistory= appointmentHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AppointmentHistory not exist with id:" + id));

        appointmentHistoryRepository.delete(appHistory);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
