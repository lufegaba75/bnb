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
public class Phone implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    private String internationalCode;
    private String phoneNumber;
    private Boolean sharePhone;

    @JsonIgnore
    @OneToOne (mappedBy = "phone")
    private User user;
}
