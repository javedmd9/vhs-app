package com.vivacom.vhs.controller;

import com.vivacom.vhs.exception.ResourceNotFoundException;
import com.vivacom.vhs.model.Bill;
import com.vivacom.vhs.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bill")
public class BillController {

    @Autowired
    public BillRepository billRepository;

    @GetMapping("/find")
    public List<Bill> getAllBills(){
        return billRepository.findAll();
    }

    @PostMapping("/add")
    public Bill createBill(@RequestBody Bill bill){
        return billRepository.save(bill);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Bill> getBillsByid(@RequestParam int id){
        Bill bill=billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription is not exist with id:" + id));
        return ResponseEntity.ok(bill);
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<Bill> updateBills(@RequestParam int id, @RequestBody Bill bill){
        Bill updateBill = billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not exist with id:" + id));
        updateBill.setAmount(bill.getAmount());
        updateBill.setDiscount(bill.getDiscount());
        updateBill.setCreatedDate(bill.getCreatedDate());
        updateBill.setModifiedDate(bill.getModifiedDate());

        billRepository.save(updateBill);

        return ResponseEntity.ok(updateBill);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deleteBill(@RequestParam int id){

        Bill bill= billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalReport not exist with id:" + id));

        billRepository.delete(bill);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
