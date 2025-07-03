package com.adil.app.repository;

import com.adil.app.domain.Lkbir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LkbirRepository extends JpaRepository<Lkbir, Integer> {

    @Query("""
                SELECT c FROM Lkbir c
                WHERE (:nomComplet IS NULL OR (c.nomComplet IS NOT NULL AND LOWER(c.nomComplet) LIKE LOWER(CONCAT('%', :nomComplet, '%'))))
                AND (:montant IS NULL OR c.montant = :montant)
            """)
    List<Lkbir> searchByNomOrMontant(@Param("nomComplet") String nomComplet, @Param("montant") Double montant);
}
