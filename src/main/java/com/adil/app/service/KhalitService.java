package com.adil.app.service;

import com.adil.app.Enumeration.CoffreNameEnum;
import com.adil.app.domain.Khalit;
import com.adil.app.dto.KhalitDTO;
import com.adil.app.dto.KhalitResponse;
import com.adil.app.exception.FunctionalException;
import com.adil.app.mapper.KhalitMapper;
import com.adil.app.repository.CoffreFortRepository;
import com.adil.app.repository.KhalitRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class KhalitService {

    private final KhalitMapper khalitMapper;
    private final KhalitRepository khalitRepository;
    private final CoffreFortRepository coffreFortRepository;
    private final EntityManager entityManager;

    public KhalitDTO create(KhalitDTO khalitDTO) {
        log.info("Start service : Creating khalit Record with details: {}", khalitDTO);

        if (khalitDTO.getId() != null && khalitRepository.existsById(khalitDTO.getId())) {
            throw new FunctionalException("Record with ID " + khalitDTO.getId() + " already exists");
        }

        var mappedKhalit = khalitMapper.toKhalit(khalitDTO);

        var coffreFort = coffreFortRepository.findByCoffreName(CoffreNameEnum.KHALIT_NAME_TABLE.getCoffreName())
                .orElseThrow(() -> new FunctionalException("CoffreFort not found with name : " + CoffreNameEnum.KHALIT_NAME_TABLE.getCoffreName()));
        coffreFort.setDateDerniereModification(LocalDate.now());
        mappedKhalit.setCoffreFort(coffreFort);
        var savedKhalit = khalitRepository.save(mappedKhalit);
        var result = khalitMapper.toKhalitDTO(savedKhalit);
        log.info("End service : Created khalit Record with details: {}", result);
        return result;
    }

    public KhalitDTO update(Integer khalitId, KhalitDTO khalitDTO) {
        log.info("Start service : Updating khalit Record with ID: {} and details: {}", khalitId, khalitDTO);
        var existingKhalit = khalitRepository.findById(khalitId)
                .orElseThrow(() -> new FunctionalException("Khalit not found with ID: " + khalitId));
        khalitMapper.updateEntity(existingKhalit, khalitDTO);
        if (existingKhalit.getCoffreFort() == null) {
            throw new FunctionalException("CoffreFort name must not be null");
        }
        existingKhalit.getCoffreFort().setDateDerniereModification(LocalDate.now());
        var updatedKhalit = khalitRepository.save(existingKhalit);
        var result = khalitMapper.toKhalitDTO(updatedKhalit);
        log.info("End service : Updated khalit Record with details: {}", result);
        return result;
    }

    public KhalitDTO getById(Integer khalitId) {
        log.info("Start service : Retrieving khalit Record with ID: {}", khalitId);
        var khalit = khalitRepository.findById(khalitId)
                .orElseThrow(() -> new FunctionalException("Khalit not found with ID: " + khalitId));
        var result = khalitMapper.toKhalitDTO(khalit);
        log.info("End service : Retrieved khalit Record with details: {}", result);
        return result;
    }

    public void delete(Integer KhalitId) {
        log.info("Start service : Deleting khalit Record with ID: {}", KhalitId);
        var existingKhalit = khalitRepository.findById(KhalitId)
                .orElseThrow(() -> new FunctionalException("khalit not found with ID: " + KhalitId));

        if (existingKhalit.getCoffreFort() == null) {
            throw new FunctionalException("CoffreFort associated with khalit must not be null");
        }

        var existingCoffreFort = existingKhalit.getCoffreFort();
        existingCoffreFort.setDateDerniereModification(LocalDate.now());
        coffreFortRepository.save(existingCoffreFort);
        khalitRepository.deleteById(existingKhalit.getId());
        log.info("End service : Deleted khalit Record with ID: {}", existingKhalit.getId());
    }

    public KhalitResponse getAll(int page, int size) {
        log.info("Start service : Retrieving all khalits Records with pagination: page={}, size={}", page, size);

        Page<KhalitDTO> khalits = khalitRepository.findAllSortedByDateRetourThenId(PageRequest.of(page, size))
                .map(khalitMapper::toKhalitDTO);

        Double totalMontant = khalitRepository.getTotalMontant();

        var coffreFort = coffreFortRepository.findByCoffreName(CoffreNameEnum.KHALIT_NAME_TABLE.getCoffreName())
                .orElseThrow(() -> new FunctionalException("CoffreFort not found with name : " + CoffreNameEnum.KHALIT_NAME_TABLE.getCoffreName()));

        LocalDate lastDateModification = Optional.ofNullable(coffreFort.getDateDerniereModification())
                .orElseThrow(() -> new FunctionalException("CoffreFort last modification date is not set"));

        Double coffreTotal = Optional.ofNullable(coffreFort.getCoffreTotal()).orElse(0.0);

        Double rest = coffreTotal - totalMontant;

        var result = KhalitResponse.builder()
                .elements(khalits.getContent())
                .rest(rest)
                .montantConsommee(totalMontant)
                .lastDateModification(lastDateModification)
                .coffreTotal(coffreTotal)
                .totalPages(khalits.getTotalPages())
                .totalElements(khalits.getTotalElements())
                .size(khalits.getSize())
                .number(khalits.getNumber())
                .first(khalits.isFirst())
                .last(khalits.isLast())
                .numberOfElements(khalits.getNumberOfElements())
                .build();
        log.info("End service : Retrieved all Khalits Records with total rest: {}", result.getRest());
        return result;
    }

    public List<KhalitDTO> searchByNomOrMontant(String nomComplet, Double montant) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Khalit> cq = cb.createQuery(Khalit.class);
        Root<Khalit> root = cq.from(Khalit.class);

        List<Predicate> predicates = new ArrayList<>();

        if (nomComplet != null) {
            // LOWER(nomComplet) LIKE LOWER('%nomComplet%')
            predicates.add(cb.like(cb.lower(root.get("nomComplet")), "%" + nomComplet.toLowerCase() + "%"));
        }

        if (montant != null) {
            predicates.add(cb.equal(root.get("montant"), montant));
        }

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        var result =  entityManager.createQuery(cq).getResultList();

        var mappedResult = result.stream()
                .map(khalitMapper::toKhalitDTO)
                .toList();

        log.info("End service : Found khalit records by name or montant, count: {}", result.size());
        return mappedResult;
    }


}
