package com.lufegaba.bnb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "customerId", referencedColumnName = "id", nullable = true)
    private User guest;

    @ManyToOne
    @JoinColumn (name = "lodgingId", referencedColumnName = "id", nullable = true)
    private Lodging lodgingReserved;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private BigDecimal price;

    private Boolean hasCanceled;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn (name = "updater", referencedColumnName = "id", nullable = true)
    private User updatedBy;

    public Reservation (User guest, Lodging lodging) {
        this.guest = guest;
        this.lodgingReserved = lodging;
    }
}
