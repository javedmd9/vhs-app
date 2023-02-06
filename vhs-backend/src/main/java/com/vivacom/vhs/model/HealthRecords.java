package com.vivacom.vhs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "healthRecords")
public class HealthRecords {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Column(name = "mobile" , unique = true)
    private String mobile;
    private String expirydate;
    private String createdDate;
    private String modifiedDate;
}
