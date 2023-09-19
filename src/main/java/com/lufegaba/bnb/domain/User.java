package com.lufegaba.bnb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn (name = "UserAddressId", referencedColumnName = "id")
    private Address address;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private Set<RoleGroup> roles;

    @JsonIgnore
    @OneToMany (mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Lodging> lodgings;

    @JsonIgnore
    @OneToMany (mappedBy = "updatedBy", cascade = CascadeType.ALL)
    private Set<Reservation> reservationsUpdated;

    @JsonIgnore
    @OneToMany (mappedBy = "guest", cascade = CascadeType.ALL)
    private Set<Reservation> reservationsMade;

    public boolean isHirer (User user) {
        var userRoles =  user.getRoles().stream().toList();
        var isHirer = false;
        for (int i = 0; i < userRoles.size(); i++) {
            if (userRoles.get(i).isHirer(userRoles.get(i))){
                isHirer = true;
            }
        }
        return isHirer;
    }
}
