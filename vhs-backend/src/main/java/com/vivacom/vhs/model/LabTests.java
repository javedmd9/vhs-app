package com.vivacom.vhs.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vivacom.vhs.constants.SampleType;
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
public class LabTests {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "test_name", nullable = false)
    private String testName;
    private String testPrefix;
    private String testDesc;
    @Column(name = "sample_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SampleType sampleType;
    private Integer testReportDuration;
    private String preCaution;
    @Column(name = "test_price", nullable = false)
    private Double testPrice;
    private Double discountRate;
    private Double handlingCharge;
    private LocalDate createdAt;
    private LocalDate modifiedAt;

    @JsonBackReference
    @ManyToMany(mappedBy = "labTestses", fetch = FetchType.LAZY)
    private Collection<Package> packages;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lab_id")
    private Lab lab;

}

