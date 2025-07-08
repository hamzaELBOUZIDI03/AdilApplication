package com.adil.app.repository;

import com.adil.app.domain.Adil3;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Adil3Repository extends JpaRepository<Adil3, Integer> {

    @Query("""
        SELECT a FROM Adil3 a
        WHERE (:nomComplet IS NULL OR (a.nomComplet IS NOT NULL AND LOWER(a.nomComplet) LIKE LOWER(CONCAT('%', :nomComplet, '%'))))
        AND (:montant IS NULL OR a.montant = :montant)
    """)
    List<Adil3> searchByNomOrMontant(@Param("nomComplet") String nomComplet, @Param("montant") Double montant);

    @Query("SELECT COALESCE(SUM(a.montant), 0) FROM Adil3 a")
    Double getTotalMontant();

    @Query("""
            SELECT a FROM Adil3 a
            ORDER BY
                CASE WHEN a.dateRetour IS NULL THEN 1 ELSE 0 END,
                a.dateRetour ASC,
                a.id ASC
            """)
    Page<Adil3> findAllSortedByDateRetourThenId(Pageable pageable);

}
