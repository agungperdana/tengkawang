package com.kratonsolution.belian.tengkawang.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.belian.tengkawang.model.Device;
import com.kratonsolution.belian.tengkawang.repository.DeviceRepository;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceService {

	@Autowired
	private DeviceRepository repo;
	
	public Optional<Device> getOne(@NonNull String serial) {
		return repo.findOneBySerial(serial);
	}
	
	public void save(@NonNull Device device) {
		
		repo.save(device);
		log.info("Saving device information {}", device.getSerial());
	}
}
