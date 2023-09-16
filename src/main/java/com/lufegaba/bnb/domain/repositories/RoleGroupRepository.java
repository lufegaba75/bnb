package com.lufegaba.bnb.domain.repositories;

import com.lufegaba.bnb.domain.RoleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleGroupRepository extends JpaRepository<RoleGroup, Integer> {
}
