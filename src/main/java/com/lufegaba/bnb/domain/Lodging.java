package com.lufegaba.bnb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lodging implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn (name = "owner_id", referencedColumnName = "id", nullable = true)
    private User owner;

    private Integer guestCapacity;

    private String name;

    private Category category;

    private BigDecimal priceByNight;

    private String description;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "locationId", referencedColumnName = "id")
    private Location location;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "lodgingAddressId", referencedColumnName = "id")
    private Address address;

    private BigDecimal reputation;

    private String checkInHour;
    private String checkOutHour;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
