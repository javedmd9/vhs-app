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
@Table(name = "labappointment")
public class LabAppointment {

    @Id
    @GeneratedValue
    private int id;
    public String type;
    public String visitingtime;
    public int patientid;
    public int labid;
    public String status;
    public String createdDate;
    public String modifiedDate;
}
