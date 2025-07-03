package com.adil.app.repository;

import com.adil.app.domain.Iltizamat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IltizamatRepository extends JpaRepository<Iltizamat, Long> {

    Page<Iltizamat> findAllByOrderByDateEcrireAsc(Pageable pageable);

}