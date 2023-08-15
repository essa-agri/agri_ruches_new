package com.agri40.filahati.management.repository;

import com.agri40.filahati.management.domain.Ruche;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Ruche entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RucheRepository extends MongoRepository<Ruche, String> {
    List<Ruche> findByRucherId(String rucher_id);

    void deleteByRucherId(String rucheId);
}
