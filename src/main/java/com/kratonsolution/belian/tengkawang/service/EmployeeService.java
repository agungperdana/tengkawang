package com.kratonsolution.belian.tengkawang.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.cache.Cache;
import com.kratonsolution.belian.tengkawang.integration.command.Command;
import com.kratonsolution.belian.tengkawang.integration.command.USERCommand;
import com.kratonsolution.belian.tengkawang.model.AttendanceOrganizationChangeEvent;
import com.kratonsolution.belian.tengkawang.model.Employee;
import com.kratonsolution.belian.tengkawang.model.Privilege;
import com.kratonsolution.belian.tengkawang.repository.EmployeeRepository;
import com.kratonsolution.belian.tengkawang.util.CommandCodeGenerator;

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
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private Cache<String, Command> commandCache;
	
	@Autowired
	private CommandCodeGenerator codeGen;

	public List<Employee> getAll() {
		return repo.findAll();
	}
	
	public List<Employee> getAll(List<String> organizations) {
		return repo.findAllByOrganizationIn(organizations);
	}

	public Optional<Employee> getById(@NonNull String id) {
		return repo.findById(id);
	}

	public Optional<Employee> getByNumber(@NonNull String number) {
		return repo.findOneByNumber(number);
	}

	public void add(@NonNull Employee employee) {

		Optional<Employee> opt = getByNumber(employee.getNumber());
		if(opt.isEmpty()) {
			
			repo.save(employee);
			
			deviceService.getAll().stream().forEach(dev -> {
				
				USERCommand command = new USERCommand(dev.getSerial(), codeGen.generate(), employee, USERCommand.UPDATE);
				commandCache.put(command.getCode(), command);
			});
			
			log.info("Creating new employe {}", employee.getFullName());
		}
		else {
			log.info("Creating new employee canceled, data already exist");
		}
	}

	public void update(@NonNull Employee employee) {

		repo.save(employee);
		
		deviceService.getAll().stream().forEach(dev -> {
			
			USERCommand command = new USERCommand(dev.getSerial(), codeGen.generate(), employee, USERCommand.UPDATE);
			commandCache.put(command.getCode(), command);
		});
		
		log.info("Updating employee data {}", employee.getFullName());
	}

	public void delete(@NonNull String id) {
		
		Optional<Employee> opt = getById(id);
		if(opt.isPresent()) {
		
			repo.delete(opt.get());

			deviceService.getAll().stream().forEach(dev -> {
				
				USERCommand command = new USERCommand(dev.getSerial(), codeGen.generate(), opt.get(), USERCommand.DELETE);
				commandCache.put(command.getCode(), command);
			});
			
			log.info("Deleting new employee");
		}
	}
	
	public Optional<Employee> createDefaultEmployee(@NonNull String number, @NonNull String organization) {
		
		Employee employee = new Employee();
		employee.setNumber(number);
		employee.setOnDeviceName(number);
		employee.setFullName(number);
		employee.setOrganization(organization);
		employee.setPrivilege(Privilege.User);
		
		repo.save(employee);
		
		return Optional.ofNullable(employee);
	}
	
	@EventListener(classes = AttendanceOrganizationChangeEvent.class)
	public void onOrganizationChange(@NonNull AttendanceOrganizationChangeEvent event) {
		
		event.getEmployeeNames().forEach(emp -> {
			
			Optional<Employee> opt = repo.findOneByNumber(emp);
			if(opt.isPresent()) {
				
				opt.get().setOrganization(event.getOrganizationName());
				repo.save(opt.get());
			}
		});
		
		log.info("Organizaation Name changed, updating employee data with {}", event.getOrganizationName());
	}
}
