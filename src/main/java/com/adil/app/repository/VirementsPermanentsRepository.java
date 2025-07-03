package com.adil.app.repository;

import com.adil.app.domain.VirementsPermanents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirementsPermanentsRepository extends JpaRepository<VirementsPermanents, Integer> {

    Page<VirementsPermanents> findAllByOrderByDateDebutAsc(Pageable pageable);

}