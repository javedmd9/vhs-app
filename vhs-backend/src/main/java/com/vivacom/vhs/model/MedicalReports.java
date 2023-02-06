package com.vivacom.vhs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicalreports")
public class MedicalReports {

    @Id
    @GeneratedValue
    private int id;
    private String type;
    private int patientid;
    private String referredby;
    private String preparedby;
    private String reportfile;
    private String createdDate;
    private String modifiedDate;
}
