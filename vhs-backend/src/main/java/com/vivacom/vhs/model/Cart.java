package com.vivacom.vhs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    public String itemType;
    public int itemId;
    public String itemName;
    public String subText;
    public String sampleType;
    public double mrp;
    public String discount;
    public double sellingPrice;
    public int reportDuration;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public Users user;
    public String precaution;
    private Integer handlingCharge;
}
