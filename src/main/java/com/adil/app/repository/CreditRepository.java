package com.adil.app.repository;

import com.adil.app.domain.Credit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Integer> {

    @Query("""
                SELECT c FROM Credit c
                WHERE (:nomComplet IS NULL OR (c.nomComplet IS NOT NULL AND LOWER(c.nomComplet) LIKE LOWER(CONCAT('%', :nomComplet, '%'))))
                AND (:montant IS NULL OR c.montant = :montant)
            """)
    List<Credit> searchByNomOrMontant(@Param("nomComplet") String nomComplet, @Param("montant") Double montant);

    @Query("SELECT COALESCE(SUM(c.montant), 0) FROM Credit c")
    Double getTotalMontant();

    @Query("""
            SELECT c FROM Credit c
            ORDER BY
                CASE WHEN c.dateRetour IS NULL THEN 1 ELSE 0 END,
                c.dateRetour ASC,
                c.id ASC
            """)
    Page<Credit> findAllSortedByDateRetourThenId(Pageable pageable);

}
