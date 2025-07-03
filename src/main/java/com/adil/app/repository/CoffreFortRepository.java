package com.adil.app.repository;

import com.adil.app.domain.CoffreFort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoffreFortRepository extends JpaRepository<CoffreFort, Integer> {

    Optional<CoffreFort> findByCoffreName(String nomTable);

}
