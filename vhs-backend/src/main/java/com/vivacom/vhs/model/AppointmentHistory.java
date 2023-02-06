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
@Table(name = "appointmenthistory")
public class AppointmentHistory {

    @Id
    @GeneratedValue
    private int id;
    private String currtype;
    private String prevtype;
    private String prevstatus;
    private String currstatus;
    private int patientid;
    private int prevdoctorid;
    private int currdoctorid;
    private String prevvisitingtime;
    private String currvisitingtime;
    private String createdDate;
    private String modifiedDate;
}
