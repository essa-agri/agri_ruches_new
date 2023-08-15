package com.agri40.filahati.management.service.impl;

import com.agri40.filahati.management.domain.Ruche;
import com.agri40.filahati.management.domain.Rucher;
import com.agri40.filahati.management.repository.RucheRepository;
import com.agri40.filahati.management.repository.RucherRepository;
import com.agri40.filahati.management.service.RucherService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Rucher}.
 */
@Service
public class RucherServiceImpl implements RucherService {

    private final Logger log = LoggerFactory.getLogger(RucherServiceImpl.class);

    private final RucherRepository rucherRepository;
    private final RucheRepository rucheRepository;

    private final Rucher currentRucher = null;

    public RucherServiceImpl(RucherRepository rucherRepository, RucheRepository rucheRepository) {
        this.rucherRepository = rucherRepository;
        this.rucheRepository = rucheRepository;
    }

    @Override
    public Rucher save(Rucher rucher) {
        log.debug("Request to save Rucher : {}", rucher);
        if (rucher.getListRuches() == null) {
            rucher.setListRuches(new ArrayList<>());
        }
        return rucherRepository.save(rucher);
    }

    @Override
    public Rucher update(Rucher rucher) {
        log.debug("Request to update Rucher : {}", rucher);
        return rucherRepository.save(rucher);
    }

    @Override
    public Optional<Rucher> partialUpdate(Rucher rucher) {
        log.debug("Request to partially update Rucher : {}", rucher);

        return rucherRepository
            .findById(rucher.getId())
            .map(existingRucher -> {
                if (rucher.getRucheName() != null) {
                    existingRucher.setRucheName(rucher.getRucheName());
                }
                if (rucher.getAdresse() != null) {
                    existingRucher.setAdresse(rucher.getAdresse());
                }
                if (rucher.getDescription() != null) {
                    existingRucher.setDescription(rucher.getDescription());
                }
                if (rucher.getUserId() != null) {
                    existingRucher.setUserId(rucher.getUserId());
                }
                if (rucher.getListRuches() != null) {
                    existingRucher.setListRuches(rucher.getListRuches());
                }

                return existingRucher;
            })
            .map(rucherRepository::save);
    }

    @Override
    public List<Rucher> findAll() {
        log.debug("Request to get all Ruchers");
        return rucherRepository.findAll();
    }

    @Override
    public Optional<Rucher> findOne(String id) {
        log.debug("Request to get Rucher : {}", id);
        return rucherRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Rucher : {}", id);
        rucherRepository.deleteById(id);
    }

    @Override
    public int getCountRechersByUserId(String user_id) {
        int num = rucherRepository.countByUserId(user_id);
        return num;
    }

    @Override
    public int getListRuchesCountById(String id) {
        Rucher rucher = rucherRepository.findById(id).orElse(null);
        if (rucher != null) {
            List<Ruche> listRuches = rucher.getListRuches();
            return (listRuches != null ? listRuches.size() : 0);
        }
        return 0;
    }

    @Override
    public List<Ruche> getListRuchesById(String id) {
        Rucher rucher = rucherRepository.findById(id).orElse(null);

        List<Ruche> listRuches = rucher.getListRuches();

        return listRuches;
    }

    @Override
    public int getListRuchesCountByUserId(String userId) {
        List<Rucher> ruchers = rucherRepository.findAllByUserId(userId);
        int totalCount = 0;
        for (Rucher rucher : ruchers) {
            List<Ruche> listRuches = rucher.getListRuches();
            totalCount += listRuches != null ? listRuches.size() : 0;
        }
        return totalCount;
    }

    @Override
    public List<Ruche> getListRuchesByUserId(String user_id) {
        List<Rucher> ruchers = rucherRepository.findAllByUserId(user_id);
        List<Ruche> ruches = new ArrayList<>();
        for (Rucher rucher : ruchers) {
            if (rucher.getListRuches() != null) ruches.addAll(rucher.getListRuches());
        }
        return ruches;
    }

    @Override
    public List<Rucher> getRuchersByUserId(String user_id) {
        return rucherRepository.findAllByUserId(user_id);
    }

    @Override
    public Rucher addRucheToRucher(String rucherId, String rucheId) {
        Optional<Rucher> optionalRucher = rucherRepository.findById(rucherId);
        Optional<Ruche> optionalRuche = rucheRepository.findById(rucheId);

        if (optionalRucher.isPresent() && optionalRuche.isPresent()) {
            Rucher rucher = optionalRucher.get();
            Ruche ruche = optionalRuche.get();
            if (rucher.getListRuches() == null) {
                rucher.setListRuches(new ArrayList<>());
            }

            rucher.getListRuches().add(ruche);

            return rucherRepository.save(rucher);
        } else {
            // Handle the case when the Rucher or Ruche with the given IDs is not found.
            // You may throw an exception or return null or an error response.
            return null;
        }
    }

    @Override
    public Rucher deleteRucheInListRuches(String rucher_id, String ruche_id) {
        Optional<Rucher> optionalRucher = rucherRepository.findById(rucher_id);
        Optional<Ruche> optionalRuche = rucheRepository.findById(ruche_id);

        if (optionalRucher.isPresent()) {
            Rucher rucher = optionalRucher.get();
            Ruche ruche = optionalRuche.get();

            rucher.getListRuches().remove(ruche);
            System.out.println("*******************************************************");
            System.out.println(rucher.getListRuches());
            System.out.println("*******************************************************");
            return rucherRepository.save(rucher);
        } else {
            //
            return null;
        }
    }
}
