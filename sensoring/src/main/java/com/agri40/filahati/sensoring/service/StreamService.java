package com.agri40.filahati.sensoring.service;

import com.agri40.filahati.sensoring.domain.Stream;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Stream}.
 */
public interface StreamService {
    /**
     * Save a stream.
     *
     * @param stream the entity to save.
     * @return the persisted entity.
     */
    Stream save(Stream stream);

    /**
     * Updates a stream.
     *
     * @param stream the entity to update.
     * @return the persisted entity.
     */
    Stream update(Stream stream);

    /**
     * Partially updates a stream.
     *
     * @param stream the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Stream> partialUpdate(Stream stream);

    /**
     * Get all the streams.
     *
     * @return the list of entities.
     */
    List<Stream> findAll();

    /**
     * Get the "id" stream.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Stream> findOne(String id);

    /**
     * Delete the "id" stream.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    Optional<Stream> getLastStream() ;

    Optional<Stream> getLastStreamByDeviceId(String device_id) ;
}
