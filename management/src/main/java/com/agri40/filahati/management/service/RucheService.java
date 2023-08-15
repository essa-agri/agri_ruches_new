package com.agri40.filahati.management.service;

import com.agri40.filahati.management.domain.Ruche;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Ruche}.
 */
public interface RucheService {
    /**
     * Save a ruche.
     *
     * @param ruche the entity to save.
     * @return the persisted entity.
     */
    Ruche save(Ruche ruche);

    /**
     * Updates a ruche.
     *
     * @param ruche the entity to update.
     * @return the persisted entity.
     */
    Ruche update(Ruche ruche);

    /**
     * Partially updates a ruche.
     *
     * @param ruche the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Ruche> partialUpdate(Ruche ruche);

    /**
     * Get all the ruches.
     *
     * @return the list of entities.
     */
    List<Ruche> findAll();

    /**
     * Get the "id" ruche.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Ruche> findOne(String id);

    /**
     * Delete the "id" ruche.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    void deleteByRucherId(String ruche_id);

    List<Ruche> findByRucherId(String rucher_id);
}
