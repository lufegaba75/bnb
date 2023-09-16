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
public class Address implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    private String country;
    private String region;
    private String city;
    private String postalCode;
    private String direction;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private User user;
}
