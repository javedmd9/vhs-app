package com.vivacom.vhs.dto;


import com.vivacom.vhs.model.Package;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageDto {
    private Integer id;
    private String name;
    private String category;
    private String description;
    private String type;
    private Double amount;
    private Double discount;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
    private List<Integer> labTestList;
    private String preCaution;
    private Integer reportDuration;
    private Integer handlingCharge;
}
