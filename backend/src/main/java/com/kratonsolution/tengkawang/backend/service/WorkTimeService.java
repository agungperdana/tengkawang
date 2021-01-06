package com.kratonsolution.tengkawang.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.tengkawang.backend.model.WorkTime;
import com.kratonsolution.tengkawang.backend.repository.WorkTimeRepository;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WorkTimeService {

	@Autowired
	private WorkTimeRepository repo;
	
	public List<WorkTime> getAll() {
		return repo.findAll();
	}
	
	public Optional<WorkTime> getByName(@NonNull String name) {
		return repo.findOneByName(name);
	}
	
	public Optional<WorkTime> getById(@NonNull String id) {
		return repo.findById(id);
	}
	
	public void add(@NonNull WorkTime workTime) {
		
		Optional<WorkTime> opt = repo.findOneByNameAndStartAndEnd(workTime.getName(), workTime.getStart(), workTime.getEnd());
		if(opt.isEmpty()) {
			
			repo.save(workTime);
		}
	}
	
	public void update(@NonNull WorkTime workTime) {
		
		repo.save(workTime);
	}
	
	public void delete(@NonNull String id) {
		repo.deleteById(id);
	}
}
