package com.adil.app.repository;

import com.adil.app.domain.AfgBuild;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AfgBuildRepository extends JpaRepository<AfgBuild, Integer> {

    Page<AfgBuild> findAllByOrderByDateEcrireAsc(Pageable pageable);

}