package com.vivacom.vhs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pharmacy")
public class Pharmacy {

    @Id
    @GeneratedValue
    private int id;
    private String email;
    private String address;
    @Column(name = "mobile" , unique = true)
    private String mobile;
    private String alternatemobile;
    private String createdDate;
    private String modifiedDate;

}
