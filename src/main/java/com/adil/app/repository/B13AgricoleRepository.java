package com.adil.app.repository;

import com.adil.app.domain.B13Agricole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface B13AgricoleRepository extends JpaRepository<B13Agricole, Long> {

    Page<B13Agricole> findAllByOrderByDateEcrireAsc(Pageable pageable);

}