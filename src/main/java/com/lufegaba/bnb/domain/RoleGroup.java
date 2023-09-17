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
public class RoleGroup implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn (name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    private Role role;

    public RoleGroup (User user, Role role){
        this.user = user;
        this.role = role;
    }

    public boolean isHirer (RoleGroup roleGroup) {
        if (roleGroup.getRole().equals(Role.HIRER)){
            return true;
        }
        return false;
    }
}
