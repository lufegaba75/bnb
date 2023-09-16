package com.lufegaba.bnb.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @Email
    private String email;

    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "phoneId", referencedColumnName = "id")
    private Phone phone;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "addressId", referencedColumnName = "id")
    private Address address;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private Set<RoleGroup> roles;
}
