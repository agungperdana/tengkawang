package com.kratonsolution.tengkawang.backend.repository;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kratonsolution.tengkawang.backend.model.WorkTime;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public interface WorkTimeRepository extends JpaRepository<WorkTime, String>{
	
	public Optional<WorkTime> findOneByName(@NonNull String name);
	
	public Optional<WorkTime> findOneByNameAndStartAndEnd(@NonNull String name, @NonNull LocalTime start, @NonNull LocalTime end);
}