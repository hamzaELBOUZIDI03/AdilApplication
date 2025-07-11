package com.adil.app.repository;

import com.adil.app.domain.HayRahmani;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HayRahmaniRepository extends JpaRepository<HayRahmani, Long> {

    Page<HayRahmani> findAllByOrderByDateEcrireAsc(Pageable pageable);

}