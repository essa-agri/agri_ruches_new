package com.agri40.filahati.management.service.impl;

import com.agri40.filahati.management.domain.Ruche;
import com.agri40.filahati.management.repository.RucheRepository;
import com.agri40.filahati.management.service.RucheService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Ruche}.
 */
@Service
public class RucheServiceImpl implements RucheService {

    private final Logger log = LoggerFactory.getLogger(RucheServiceImpl.class);

    private final RucheRepository rucheRepository;

    public RucheServiceImpl(RucheRepository rucheRepository) {
        this.rucheRepository = rucheRepository;
    }

    @Override
    public Ruche save(Ruche ruche) {
        log.debug("Request to save Ruche : {}", ruche);
        return rucheRepository.save(ruche);
    }

    @Override
    public Ruche update(Ruche ruche) {
        log.debug("Request to update Ruche : {}", ruche);
        return rucheRepository.save(ruche);
    }

    @Override
    public Optional<Ruche> partialUpdate(Ruche ruche) {
        log.debug("Request to partially update Ruche : {}", ruche);

        return rucheRepository
            .findById(ruche.getId())
            .map(existingRuche -> {
                if (ruche.getRucheName() != null) {
                    existingRuche.setRucheName(ruche.getRucheName());
                }
                if (ruche.getIndentifiant() != null) {
                    existingRuche.setIndentifiant(ruche.getIndentifiant());
                }
                if (ruche.getDescription() != null) {
                    existingRuche.setDescription(ruche.getDescription());
                }
                if (ruche.getRucherId() != null) {
                    existingRuche.setRucherId(ruche.getRucherId());
                }
                if (ruche.getDeviceId() != null) {
                    existingRuche.setDeviceId(ruche.getDeviceId());
                }

                return existingRuche;
            })
            .map(rucheRepository::save);
    }

    @Override
    public List<Ruche> findAll() {
        log.debug("Request to get all Ruches");
        return rucheRepository.findAll();
    }

    @Override
    public Optional<Ruche> findOne(String id) {
        log.debug("Request to get Ruche : {}", id);
        return rucheRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Ruche : {}", id);
        rucheRepository.deleteById(id);
    }

    @Override
    public void deleteByRucherId(String ruche_id) {
        rucheRepository.deleteByRucherId(ruche_id);
    }

    @Override
    public List<Ruche> findByRucherId(String rucher_id) {
        return rucheRepository.findByRucherId(rucher_id);
    }
}
