package com.vivacom.vhs.dto;

import com.vivacom.vhs.constants.SampleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabTestDto {
    private Integer id;
    private String testName;
    private String testPrefix;
    private String testDesc;
    private SampleType sampleType;
    private Integer testReportDuration;
    private String preCaution;
    private Double testPrice;
    private Double discountRate;
    private Double handlingCharge;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
    private List<Integer> packages;
    private Integer labId;
}
