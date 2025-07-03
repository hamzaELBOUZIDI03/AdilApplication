package com.adil.app.repository;

import com.adil.app.domain.Investissement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestissementRepository extends JpaRepository<Investissement, Long> {

    Page<Investissement> findAllByOrderByDateEcrireAsc(Pageable pageable);

}