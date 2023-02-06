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
@Table(name = "healthRecordslist")
public class HealthRecordslist {

    @Id
    @GeneratedValue
    private int id;
    private String currname;
    private String prevname;
    private String currmobile;
    private String prevmobile;
    private String prevexpirydate;
    private String currexpirydate;
    private String lastmodifiedDate;
    private String currmodifiedDate;
    private String createdDate;
}
