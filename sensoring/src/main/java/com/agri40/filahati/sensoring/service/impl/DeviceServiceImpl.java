package com.agri40.filahati.sensoring.service.impl;

import com.agri40.filahati.sensoring.domain.Device;
import com.agri40.filahati.sensoring.repository.DeviceRepository;
import com.agri40.filahati.sensoring.service.DeviceService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Device}.
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    private final Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);

    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Device save(Device device) {
        log.debug("Request to save Device : {}", device);
        return deviceRepository.save(device);
    }

    @Override
    public Device update(Device device) {
        log.debug("Request to update Device : {}", device);
        return deviceRepository.save(device);
    }

    @Override
    public Optional<Device> partialUpdate(Device device) {
        log.debug("Request to partially update Device : {}", device);

        return deviceRepository
            .findById(device.getId())
            .map(existingDevice -> {
                if (device.getName() != null) {
                    existingDevice.setName(device.getName());
                }
                if (device.getRucheId() != null) {
                    existingDevice.setRucheId(device.getRucheId());
                }
                if (device.getStatus() != null) {
                    existingDevice.setStatus(device.getStatus());
                }

                return existingDevice;
            })
            .map(deviceRepository::save);
    }

    @Override
    public List<Device> findAll() {
        log.debug("Request to get all Devices");
        return deviceRepository.findAll();
    }

    @Override
    public Optional<Device> findOne(String id) {
        log.debug("Request to get Device : {}", id);
        return deviceRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Device : {}", id);
        deviceRepository.deleteById(id);
    }
}
