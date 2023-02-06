package com.vivacom.vhs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lab")
public class Lab {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String email;
    @Column(name = "mobile" , unique = true)
    private String mobile;
    private String alternateMobile;
    private String address;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String status;
    private String verified;
    private String createdDate;
    private String modifiedDate;
}
