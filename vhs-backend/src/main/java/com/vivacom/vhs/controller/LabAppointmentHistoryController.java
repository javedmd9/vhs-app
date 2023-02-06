package com.vivacom.vhs.controller;

import com.vivacom.vhs.exception.ResourceNotFoundException;
import com.vivacom.vhs.model.LabAppointmentHistory;
import com.vivacom.vhs.repository.LabAppointmentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labAppHistory")
public class LabAppointmentHistoryController {

    @Autowired
    public LabAppointmentHistoryRepository labAppointmentHistoryRepository;

    @GetMapping("/find")
    public List<LabAppointmentHistory> getAllLabAppHistory(){
        return labAppointmentHistoryRepository.findAll();
    }

    @PostMapping("/add")
    public LabAppointmentHistory createLabAppHistory(@RequestBody LabAppointmentHistory labappointmentHistory){
        return labAppointmentHistoryRepository.save(labappointmentHistory);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<LabAppointmentHistory> geLabAppHistoryByid(@RequestParam int id){
        LabAppointmentHistory labappointmentHistory=labAppointmentHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LabAppointmentHistory is not exist with id:" + id));
        return ResponseEntity.ok(labappointmentHistory);
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<LabAppointmentHistory> updateLabAppHistory(@RequestParam int id, @RequestBody LabAppointmentHistory labappHistoryDtls){
        LabAppointmentHistory updatelabAppHistory = labAppointmentHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LabAppointmentHistory not exist with id:" + id));
        updatelabAppHistory.setCurrtype(labappHistoryDtls.getCurrtype());
        updatelabAppHistory.setPrevtype(labappHistoryDtls.getPrevtype());
        updatelabAppHistory.setCurrstatus(labappHistoryDtls.getCurrstatus());
        updatelabAppHistory.setPrevstatus(labappHistoryDtls.getPrevstatus());
        updatelabAppHistory.setPatientid(labappHistoryDtls.getPatientid());
        updatelabAppHistory.setPrevlabid(labappHistoryDtls.getPrevlabid());
        updatelabAppHistory.setCurrlabid(labappHistoryDtls.getCurrlabid());
        updatelabAppHistory.setPrevvisitingtime(labappHistoryDtls.getPrevvisitingtime());
        updatelabAppHistory.setCurrvisitingtime(labappHistoryDtls.getCurrvisitingtime());
        updatelabAppHistory.setCreatedDate(labappHistoryDtls.getCreatedDate());
        updatelabAppHistory.setModifiedDate(labappHistoryDtls.getModifiedDate());

        labAppointmentHistoryRepository.save(updatelabAppHistory);

        return ResponseEntity.ok(updatelabAppHistory);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deleteLabAppHistory(@RequestParam int id){

        LabAppointmentHistory labappHistory= labAppointmentHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LabAppointmentHistory not exist with id:" + id));

        labAppointmentHistoryRepository.delete(labappHistory);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
