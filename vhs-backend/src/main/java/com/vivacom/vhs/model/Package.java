package com.vivacom.vhs.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private String category;
    private String description;
    private String type;
    private Double amount;
    private Double discount;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
    private String preCaution;
    private Integer reportDuration;
    private Integer handlingCharge;


    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "package_lab_testses",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "lab_testses_id"))
    private Collection<LabTests> labTestses;

}
