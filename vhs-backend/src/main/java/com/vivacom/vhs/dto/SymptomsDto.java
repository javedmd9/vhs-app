package com.vivacom.vhs.dto;

import com.vivacom.vhs.model.Symptoms;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SymptomsDto extends Symptoms {
    private Integer departmentId;
}
