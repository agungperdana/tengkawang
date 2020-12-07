package com.kratonsolution.belian.tengkawang.backoffice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Controller
public class Home {
	
	@GetMapping("/")
	public String welcome(){
		
		return "dashboard";
	}
}
