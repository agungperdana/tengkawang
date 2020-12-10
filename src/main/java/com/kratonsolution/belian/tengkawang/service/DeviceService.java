package com.kratonsolution.belian.tengkawang.service;

import java.util.List;
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
	
	public Optional<Device> getOneBySerial(@NonNull String serial) {
		return repo.findOneBySerial(serial);
	}
	
	public Optional<Device> getOneById(@NonNull String id) {
		return repo.findById(id);
	}
	
	public List<Device> getAll() {
		return repo.findAll();
	}
	
	public List<Device> getAllByOrganization(@NonNull String organization) {
		return repo.findAllByOrganization(organization);
	}
	
	public void add(@NonNull Device device) {
		
		Optional<Device> opt = getOneBySerial(device.getSerial());
		if(opt.isEmpty()) {
			repo.save(device);
			log.info("Saving device information {}", device.getSerial());
		}
		else {
			log.info("Creation of new device canceled, device already exist {}", device.getSerial());
		}
	}
	
	public void update(@NonNull Device device) {
		repo.save(device);
		log.info("Updating device {}", device.getSerial());
	}
	
	public void delete(@NonNull String id) {
		repo.deleteById(id);
		log.info("Deleting device {}", id);
	}
}
