package com.kratonsolution.tengkawang.api.v1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kratonsolution.tengkawang.backend.model.WorkTime;
import com.kratonsolution.tengkawang.backend.service.WorkTimeService;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@RestController
public class WorkTimeApiV1 {

	@Autowired
	private WorkTimeService service;
		
	@Secured("ROLE_API_V1_WORKTIME_READ")
	@GetMapping("/api/v1/worktimes")
	public List<WorkTime> list(Model model) {
		return service.getAll();
	}
	
	@Secured("ROLE_API_V1_WORKTIME_CREATE")
	@PostMapping("/api/v1/worktimes-add")
	public WorkTime add(@RequestBody WorkTime worktime ) throws Exception{
		
		service.add(worktime);
		return worktime;
	}
	
	@Secured("ROLE_API_V1_WORKTIME_UPDATE")
	@PostMapping("/api/v1/worktimes-update")
	public WorkTime edit(@RequestBody WorkTime worktime) throws Exception {
	
		Optional<WorkTime> opt = service.getById(worktime.getId());
		if(opt.isPresent()) {
			
			opt.get().setName(worktime.getName());
			opt.get().setValidFrom(worktime.getValidFrom());
			opt.get().setValidTo(worktime.getValidTo());
			opt.get().setStart(worktime.getStart());
			opt.get().setEnd(worktime.getEnd());
			opt.get().setType(worktime.getType());
			opt.get().setComment(worktime.getComment());
			
			service.update(opt.get());
		}
		
		return worktime;
	}
	
	@Secured("ROLE_API_V1_WORKTIME_DELETE")
	@DeleteMapping("/api/v1/worktimes-delete/{id}")
	public WorkTime delete(@PathVariable String id) {
		
		Optional<WorkTime> opt = service.getById(id);
		if(opt.isPresent()) {
			service.delete(id);
		}

		return opt.get();
	}
}
