package com.lufegaba.bnb.domain.repositories;

import com.lufegaba.bnb.domain.Lodging;
import com.lufegaba.bnb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LodgingRepository extends JpaRepository<Lodging, Integer> {
    List<Lodging> getLodgingsByOwner (User owner);
}
