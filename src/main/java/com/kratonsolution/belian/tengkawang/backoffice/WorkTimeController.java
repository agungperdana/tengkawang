package com.kratonsolution.belian.tengkawang.backoffice;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kratonsolution.belian.tengkawang.model.AttendanceEventType;
import com.kratonsolution.belian.tengkawang.model.WorkTime;
import com.kratonsolution.belian.tengkawang.service.WorkTimeService;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Controller
public class WorkTimeController {

	@Autowired
	private WorkTimeService service;
		
	@GetMapping("/backoffice/worktimes")
	public String list(Model model) {
		model.addAttribute("worktimes", service.getAll());
		return "worktimes/table";
	}
	
	@GetMapping("/backoffice/worktimes-pre-add")
	public String preadd() {
		
		return "worktimes/add";
	}
	
	@PostMapping("/backoffice/worktimes-add")
	public String add(@RequestParam("name")String name,
			@RequestParam("start")LocalTime start,
			@RequestParam("end")LocalTime end,
			@RequestParam("type")AttendanceEventType type,
			@RequestParam("comment")Optional<String> comment) {
		
		WorkTime worktime = new WorkTime();
		worktime.setName(name);
		worktime.setStart(start);
		worktime.setEnd(end);
		worktime.setType(type);
		worktime.setComment(comment.get());
		
		service.add(worktime);
		
		return "redirect:/backoffice/worktimes";
	}
	
	@GetMapping("/backoffice/worktimes-pre-edit")
	public String preedit(@RequestParam("id")String id, Model model) {
	
		model.addAttribute("worktime", service.getById(id).get());
		return "worktimes/edit";
	}
	
	@PostMapping("/backoffice/worktimes-edit")
	public String edit(@RequestParam("id")String id, 
					   @RequestParam("name")String name,
					   @RequestParam("start")LocalTime start,
					   @RequestParam("end")LocalTime end,
					   @RequestParam("type")AttendanceEventType type,
					   @RequestParam("comment")Optional<String> comment,
						Model model) {
	
		Optional<WorkTime> opt = service.getById(id);
		if(opt.isPresent()) {
			
			opt.get().setName(name);
			opt.get().setStart(start);
			opt.get().setEnd(end);
			opt.get().setType(type);
			opt.get().setComment(comment.get());
			
			service.update(opt.get());
		}
		
		return "redirect:/backoffice/worktimes";
	}
	
	@GetMapping("/backoffice/worktimes-delete")
	public String delete(@RequestParam("id")String id) {
		
		service.delete(id);
		return "redirect:/backoffice/worktimes";
	}
}
