package com.adil.app.repository;

import com.adil.app.domain.Identifiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentifiantRepository extends JpaRepository<Identifiant, Integer> {
}
