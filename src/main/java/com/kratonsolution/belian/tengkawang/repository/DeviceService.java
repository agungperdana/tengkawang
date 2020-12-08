package com.kratonsolution.belian.tengkawang.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.belian.tengkawang.model.Device;

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
	
	public void register(@NonNull String serial, @NonNull String ip) {
		
		Optional<Device> opt = repo.findOneBySerial(serial);
		if(!opt.isPresent()) {
			
			Device device = new Device(serial, "Device", ip);
			repo.save(device);
			log.info("Registering new device with serial {}", serial);
			return;
		}
		
		log.info("Device with serial {} already exist", serial);
	}
}
