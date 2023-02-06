package com.vivacom.vhs.controller;

import com.vivacom.vhs.exception.ResourceNotFoundException;
import com.vivacom.vhs.model.Medicine;
import com.vivacom.vhs.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {

    @Autowired
    public MedicineRepository medicineRepository;


    @GetMapping("/find")
    public List<Medicine> getAllMedicines(){
        return medicineRepository.findAll();
    }

    @PostMapping("/add")
    public Medicine createMedicine(@RequestBody Medicine medicine){
        return medicineRepository.save(medicine);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Medicine> getMedicineByid(@RequestParam int id){
        Medicine medicine=medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine is not exist with id:" + id));
        return ResponseEntity.ok(medicine);
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<Medicine> updateMedicine(@RequestParam int id, @RequestBody Medicine medicine){
        Medicine updateMedicine = medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine not exist with id:" + id));
        updateMedicine.setAmount(medicine.getAmount());
        updateMedicine.setType(medicine.getType());
        updateMedicine.setExpdate(medicine.getExpdate());
        updateMedicine.setCreatedDate(medicine.getCreatedDate());
        updateMedicine.setModifiedDate(medicine.getModifiedDate());

        medicineRepository.save(updateMedicine);

        return ResponseEntity.ok(updateMedicine);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deleteMedicine(@RequestParam int id){

        Medicine medicine= medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine not exist with id:" + id));

        medicineRepository.delete(medicine);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
