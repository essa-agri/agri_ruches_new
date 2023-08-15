package com.agri40.filahati.management.service;

import com.agri40.filahati.management.domain.Ruche;
import com.agri40.filahati.management.domain.Rucher;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Rucher}.
 */
public interface RucherService {
    /**
     * Save a rucher.
     *
     * @param rucher the entity to save.
     * @return the persisted entity.
     */
    Rucher save(Rucher rucher);

    /**
     * Updates a rucher.
     *
     * @param rucher the entity to update.
     * @return the persisted entity.
     */
    Rucher update(Rucher rucher);

    /**
     * Partially updates a rucher.
     *
     * @param rucher the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Rucher> partialUpdate(Rucher rucher);

    /**
     * Get all the ruchers.
     *
     * @return the list of entities.
     */
    List<Rucher> findAll();

    /**
     * Get the "id" rucher.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Rucher> findOne(String id);

    /**
     * Delete the "id" rucher.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    //

    int getCountRechersByUserId(String user_id);

    int getListRuchesCountById(String id);

    List<Ruche> getListRuchesById(String id);

    int getListRuchesCountByUserId(String user_id);

    List<Ruche> getListRuchesByUserId(String user_id);

    List<Rucher> getRuchersByUserId(String user_id);

    Rucher addRucheToRucher(String rucherId, String rucheId);

    Rucher deleteRucheInListRuches(String rucher_id, String ruche_id);
}
