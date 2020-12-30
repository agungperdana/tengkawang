package com.kratonsolution.belian.tengkawang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kratonsolution.belian.tengkawang.model.Device;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public interface DeviceRepository extends JpaRepository<Device, String> {

	Optional<Device> findOneBySerial(@NonNull String serial);
	
	List<Device> findAllByOrganization(@NonNull String organization);
	
	List<Device> findAllByOrganizationIn(@NonNull List<String> organizations);
	
}
