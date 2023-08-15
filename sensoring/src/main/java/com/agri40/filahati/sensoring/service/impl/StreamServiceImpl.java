package com.agri40.filahati.sensoring.service.impl;

import com.agri40.filahati.sensoring.domain.Stream;
import com.agri40.filahati.sensoring.repository.StreamRepository;
import com.agri40.filahati.sensoring.service.StreamService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Stream}.
 */
@Service
public class StreamServiceImpl implements StreamService {

    private final Logger log = LoggerFactory.getLogger(StreamServiceImpl.class);

    private final StreamRepository streamRepository;
    private final MongoTemplate mongoTemplate; // Inject the MongoTemplate
    public StreamServiceImpl(StreamRepository streamRepository , MongoTemplate mongoTemplate) {
        this.streamRepository = streamRepository;
        this.mongoTemplate    = mongoTemplate ;
    }

    @Override
    public Stream save(Stream stream) {
        log.debug("Request to save Stream : {}", stream);
        return streamRepository.save(stream);
    }

    @Override
    public Stream update(Stream stream) {
        log.debug("Request to update Stream : {}", stream);
        return streamRepository.save(stream);
    }

    @Override
    public Optional<Stream> partialUpdate(Stream stream) {
        log.debug("Request to partially update Stream : {}", stream);

        return streamRepository
            .findById(stream.getId())
            .map(existingStream -> {
                if (stream.getDeviceId() != null) {
                    existingStream.setDeviceId(stream.getDeviceId());
                }
                if (stream.getParams() != null) {
                    existingStream.setParams(stream.getParams());
                }
                if (stream.getCreatedAt() != null) {
                    existingStream.setCreatedAt(stream.getCreatedAt());
                }

                return existingStream;
            })
            .map(streamRepository::save);
    }

    @Override
    public List<Stream> findAll() {
        log.debug("Request to get all Streams");
        return streamRepository.findAll();
    }

    @Override
    public Optional<Stream> findOne(String id) {
        log.debug("Request to get Stream : {}", id);
        return streamRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Stream : {}", id);
        streamRepository.deleteById(id);
    }

    @Override
    public Optional<Stream> getLastStream() {
        Sort sort = Sort.by(Sort.Direction.DESC, "_id");
        List<Stream> streams = streamRepository.findAll(sort);
        if (!streams.isEmpty()) {
            return Optional.of(streams.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Stream> getLastStreamByDeviceId(String device_id) {
        Sort sort = Sort.by(Sort.Direction.DESC, "_id");
        Query query = new Query(Criteria.where("device_id").is(device_id)).with(sort);

        List<Stream> streams = mongoTemplate.find( query, Stream.class);
        if (!streams.isEmpty()) {
            return Optional.of(streams.get(0));
        } else {
            return Optional.empty();
        }
    }
}
