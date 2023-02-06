package com.vivacom.vhs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String email;
    @Column(name = "mobile" , unique = true)
    private String mobile;
    private String alternatemobile;
    private String specialization;
    private String address;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String status;
    private String certificate1;
    private String certificate2;
    private String certificate3;
    private String createdDate;
    private String modifiedDate;
}
