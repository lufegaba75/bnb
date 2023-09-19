package com.lufegaba.bnb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Extras implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @JsonIgnore
    @OneToOne (mappedBy = "extras")
    private Lodging lodging;

    private Boolean hasWheelChairAccess;
    private Boolean hasKitchen;
    private Boolean hasInternet;
    private Boolean hasTV;
    private Boolean hasLaundry;
    private Boolean hasWcAdjust;
    private Boolean hasShowerAdjust;
    private Boolean hasParking;
    private Boolean hasElevator;
}
