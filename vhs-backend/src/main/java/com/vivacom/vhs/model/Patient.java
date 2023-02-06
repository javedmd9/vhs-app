package com.vivacom.vhs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String email;
    @Column(name = "mobile" , unique = true)
    private String mobile;
    private String alternatemobile;
    private String address;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String nationaltype;
    private String nationalid;
    private String createdDate;
    private String modifiedDate;
    private int healthRecordsid;
}
