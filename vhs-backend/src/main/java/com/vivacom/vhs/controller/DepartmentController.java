package com.vivacom.vhs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vivacom.vhs.constants.FileType;
import com.vivacom.vhs.model.Department;
import com.vivacom.vhs.repository.DepartmentRepository;
import com.vivacom.vhs.storage.FileService;
import com.vivacom.vhs.constants.Status;
import com.vivacom.vhs.utils.FormDataWithFile;
import com.vivacom.vhs.utils.StatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private FileService storageService;

    @Autowired
    private ObjectMapper objectMapper;



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public StatusDto saveNewSpecialization(@ModelAttribute FormDataWithFile formData){
        StatusDto status = new StatusDto();

        try{
            Department department = objectMapper.readValue(formData.getDto(), Department.class);
            Department existingDepartment = departmentRepository.findByName(department.getName());
            if (existingDepartment != null){
                return StatusDto.builder().result(Status.FAILED).message("Specialization with this name is already present.").build();
            }
            int newUniqueId = generateNextId();
            department.setId(newUniqueId);
            String fileName = String.valueOf(newUniqueId);


            StatusDto statusDto = storageService.uploadFile(formData.getImageFile(), fileName, FileType.IMAGE_FILE);
            department.setIconPath(statusDto.getMessage());
            if (!statusDto.getResult().equalsIgnoreCase(Status.SUCCESS)){
                return StatusDto.builder().result(Status.FAILED).message("Exception while uploading image").build();
            }
            departmentRepository.save(department);
            status.setResult(Status.SUCCESS);
            status.setMessage("Successfully saved new department");
        } catch (Exception e){
            e.printStackTrace();
            status.setResult(Status.FAILED);
            status.setMessage("Some exception occurred");
        }
        return status;
    }

    @RequestMapping(value = "/view-all", method = RequestMethod.GET)
    public List<Department> viewAll(Model model){
        List<Department> departments = departmentRepository.findAll();
//        model.addAttribute("departments", departments);
//        return "hello";
        return departments;
    }

    private Integer generateNextId(){
        Department department = departmentRepository.findFirstByOrderByIdDesc();
        int uniqueId = 0;
        if (department != null){
            uniqueId = department.getId() + 1;
        } else {
            uniqueId = 1;
        }
        return uniqueId;
    }
}
