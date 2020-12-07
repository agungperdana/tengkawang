package com.kratonsolution.belian.tengkawang.server;

import java.time.Instant;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@RestController
public class HandShakeController {

	@GetMapping("/adms-server/cdata")
	public void handshake(@RequestParam("SN")Optional<String> serial, 
						  @RequestParam("options")Optional<String> options, 
						  HttpServletResponse response) {
		
		System.out.println(serial.orElse("empty param SN"));
		System.out.println(options.orElse("empty param options"));
		
		response.addHeader("Stamp", Instant.now().toString());
		response.addHeader("OpStamp", Instant.now().toString());
		response.addHeader("ErrorDelay", "60");
		response.addHeader("Delay", "30");
		response.addHeader("TransTimes", "00:00;14:05");
		response.addHeader("TransInterval", "1");
		response.addHeader("TransFlag","1111000000");
		response.addHeader("Realtime", "1");
		response.addHeader("Encrypt", "0");
	}
}
