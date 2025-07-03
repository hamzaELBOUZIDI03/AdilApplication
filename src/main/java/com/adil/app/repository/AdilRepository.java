package com.adil.app.repository;

import com.adil.app.domain.Adil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdilRepository extends JpaRepository<Adil, Integer> {

    @Query("""
        SELECT a FROM Adil a
        WHERE (:nomComplet IS NULL OR (a.nomComplet IS NOT NULL AND LOWER(a.nomComplet) LIKE LOWER(CONCAT('%', :nomComplet, '%'))))
        AND (:montant IS NULL OR a.montant = :montant)
    """)
    List<Adil> searchByNomOrMontant(@Param("nomComplet") String nomComplet, @Param("montant") Double montant);

}
