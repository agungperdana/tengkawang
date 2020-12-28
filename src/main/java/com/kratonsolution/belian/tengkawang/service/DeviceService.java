package com.kratonsolution.belian.tengkawang.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.belian.tengkawang.model.Device;
import com.kratonsolution.belian.tengkawang.model.DeviceOrganizationChangeEvent;
import com.kratonsolution.belian.tengkawang.model.DeviceStatus;
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
	
	@Autowired
	private ApplicationEventPublisher pub;
		
	public Optional<Device> getBySerial(@NonNull String serial) {
		return repo.findOneBySerial(serial);
	}
	
	public Optional<Device> getById(@NonNull String id) {
		return repo.findById(id);
	}
	
	public List<Device> getAll() {
		return repo.findAll();
	}
	
	public List<Device> getAll(@NonNull List<String> organizations) {
		return repo.findAllByOrganizationIn(organizations);
	}
	
	public List<Device> getAllByOrganization(@NonNull String organization) {
		return repo.findAllByOrganization(organization);
	}
	
	public void add(@NonNull Device device) {
		
		Optional<Device> opt = getBySerial(device.getSerial());
		if(opt.isEmpty()) {
			repo.save(device);
			log.info("Saving device information {}", device.getSerial());
		}
		else {
			log.info("Creation of new device canceled, device already exist {}", device.getSerial());
		}
	}
	
	public void update(String id, 
						String serial,
						Optional<String> name,
						Optional<String> ip,
						Optional<String> company,
						Optional<String> option,
						Optional<String> comment) {

		boolean organizationHasChanged = false;
		
		Optional<Device> opt = repo.findById(id);
		if(opt.isPresent()) {

			organizationHasChanged = company.isPresent() && !opt.get().getOrganization().equals(company.get());
			
			opt.get().setComment(comment.get());
			opt.get().setIp(ip.get());
			opt.get().setName(name.get());
			opt.get().setOption(option.get());
			opt.get().setOrganization(company.get());
			opt.get().setSerial(serial);
			opt.get().setOption(option.get());
			
			repo.save(opt.get());
			log.info("Updating device {}", serial);
			
			if(organizationHasChanged) {

				pub.publishEvent(new DeviceOrganizationChangeEvent(getClass().getName(), serial, company.get()));
			}
		}
	}
	
	public void online(@NonNull String serial, @NonNull Optional<String> ip) {
		
		Optional<Device> opt = repo.findOneBySerial(serial);
		if(opt.isPresent()) {
			
			opt.get().setStatus(DeviceStatus.Online);
			opt.get().setId(ip.orElse(null));
		}
	}
	
	public void adhocRegistration(@NonNull String serial, @NonNull String ip) {
		
		Device device = new Device();
		device.setComment("Auto Generated on first handshake");
		device.setIp(ip);
		device.setName(serial);
		device.setSerial(serial);
		device.setOrganization("DEFT ORG");
		device.setStatus(DeviceStatus.Online);
		
		repo.save(device);
		log.info("Creating new device based on realtime device handshake, note: Admin need to assign this device into actual company");
	}
	
	public void delete(@NonNull String id) {
		repo.deleteById(id);
		log.info("Deleting device {}", id);
	}
	
	public static void main(String[] args) {
		System.out.println(Base64.getEncoder().encodeToString("xnd_development_fABUo7n1o8i43UZPpN2q54HvS5NRqcfwRqw8e410sSaytm2oAOZNj9cw08OdXO5".getBytes()));
	}
}
