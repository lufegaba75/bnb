package com.lufegaba.bnb.domain.repositories;

import com.lufegaba.bnb.domain.Extras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtrasRepository extends JpaRepository<Extras, Integer> {
}
