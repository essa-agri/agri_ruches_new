package com.agri40.filahati.management.repository;

import com.agri40.filahati.management.domain.Rucher;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Rucher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RucherRepository extends MongoRepository<Rucher, String> {
    int countByUserId(String userId);

    int getCountListRuchesById(String id);

    List<Rucher> findAllByUserId(String userId);
}
