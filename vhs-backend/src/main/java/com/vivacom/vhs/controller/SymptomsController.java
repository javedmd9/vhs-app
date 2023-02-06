package com.vivacom.vhs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vivacom.vhs.constants.FileType;
import com.vivacom.vhs.dto.SymptomsDto;
import com.vivacom.vhs.model.Department;
import com.vivacom.vhs.model.Symptoms;
import com.vivacom.vhs.repository.DepartmentRepository;
import com.vivacom.vhs.repository.SymptomRepository;
import com.vivacom.vhs.storage.FileService;
import com.vivacom.vhs.constants.Status;
import com.vivacom.vhs.utils.FormDataWithFile;
import com.vivacom.vhs.utils.StatusDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/symptoms")
public class SymptomsController {

    @Autowired
    private SymptomRepository symptomRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public StatusDto createNewSymptom(@ModelAttribute FormDataWithFile formData){
        StatusDto statusDto = new StatusDto();
        try{
            SymptomsDto symptomsDto = objectMapper.readValue(formData.getDto(), SymptomsDto.class);
            Symptoms existingSymptoms = symptomRepository.findByName(symptomsDto.getName());
            if (existingSymptoms != null){
                return StatusDto.builder().result(Status.FAILED).message("Symptom with this name already present").build();
            }
            String imageName = RandomStringUtils.randomAlphanumeric(9).toUpperCase();
            StatusDto fileUploadResponse = fileService.uploadFile(formData.getImageFile(), imageName, FileType.IMAGE_FILE);
            if (fileUploadResponse.getResult().equalsIgnoreCase(Status.FAILED)){
                return StatusDto.builder().result(Status.FAILED).build();
            }
            symptomsDto.setIconPath(fileUploadResponse.getMessage());

            Symptoms symptoms = convertSymptomDtoToSymptom(symptomsDto);
            symptomRepository.save(symptoms);
            statusDto.setResult(Status.SUCCESS);
            statusDto.setMessage("New symptom saved successfully");
        } catch (Exception e){
            e.printStackTrace();
            statusDto.setResult(Status.FAILED);
            statusDto.setMessage("Some exception occurred");
        }
        return statusDto;
    }

    @RequestMapping(value = "/view-all", method = RequestMethod.GET)
    public List<Symptoms> viewAllSymptoms(){
        return symptomRepository.findAll();
    }

    private Symptoms convertSymptomDtoToSymptom(SymptomsDto dto) throws Exception {
        Symptoms symptoms = new Symptoms();
        symptoms.setName(dto.getName());
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new Exception(String.format("Department with id: %d not found", dto.getDepartmentId())));
        symptoms.setDepartment(department);
        symptoms.setIconPath(dto.getIconPath());
        return symptoms;
    }
}
