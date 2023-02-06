package com.vivacom.vhs.controller;

import com.vivacom.vhs.constants.Status;
import com.vivacom.vhs.exception.ResourceNotFoundException;
import com.vivacom.vhs.model.Doctor;
import com.vivacom.vhs.model.Lab;
import com.vivacom.vhs.repository.LabRepository;
import com.vivacom.vhs.utils.StatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lab")
public class LabController {

    @Autowired
    public LabRepository labRepository;


    @GetMapping("/find")
    public List<Lab> getAllLabs(){
        return labRepository.findAll();
    }

    @PostMapping("/add")
    public StatusDto createLab(@RequestBody Lab lab){
        if (lab.getId() != null){
            return updateLab(lab);
        }
        labRepository.save(lab);
        return StatusDto.builder().result(Status.SUCCESS).message("Successfully created new lab").build();
    }

    public StatusDto updateLab(Lab lab){
        Lab updateLab = labRepository.findById(lab.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Lab not exist with id or id is null"));
        updateLab.setName(lab.getName());
        updateLab.setEmail(lab.getEmail());
        updateLab.setMobile(lab.getMobile());
        updateLab.setAlternateMobile(lab.getAlternateMobile());
        updateLab.setAddress(lab.getAddress());
        updateLab.setCity(lab.getCity());
        updateLab.setCountry(lab.getCountry());
        updateLab.setState(lab.getState());
        updateLab.setPincode(lab.getPincode());
        updateLab.setStatus(lab.getStatus());
        updateLab.setVerified(lab.getVerified());
        updateLab.setCreatedDate(lab.getCreatedDate());
        updateLab.setModifiedDate(lab.getModifiedDate());

        labRepository.save(updateLab);

        return StatusDto.builder().result(Status.SUCCESS).message("Successfully updated the lab").build();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deleteLab(@RequestParam int id){

        Lab lab = labRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lab not exist with id:" + id));

        labRepository.delete(lab);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
