package com.lufegaba.bnb.domain.repositories;

import com.lufegaba.bnb.domain.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Integer> {
}
