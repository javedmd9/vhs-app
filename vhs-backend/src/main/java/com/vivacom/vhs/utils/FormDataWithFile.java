package com.vivacom.vhs.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormDataWithFile {
    private MultipartFile imageFile;
    private String dto;
}
