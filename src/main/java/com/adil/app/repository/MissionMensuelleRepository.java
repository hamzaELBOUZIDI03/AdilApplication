package com.adil.app.repository;

import com.adil.app.domain.MissionMensuelle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionMensuelleRepository extends JpaRepository<MissionMensuelle, Integer> {

    Page<MissionMensuelle> findAllByOrderByIdAsc(Pageable pageable);

}