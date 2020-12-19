package com.kratonsolution.belian.tengkawang.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.kratonsolution.belian.tengkawang.auth.SecurityInformation;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Slf4j
public class Securitys {
	
	public static final List<String> getOrganizations(Object principal) {
		
		List<String> list = new ArrayList<>();
		
		if(principal != null) {
			
			if(principal instanceof SecurityInformation) {

				SecurityInformation info = (SecurityInformation)principal;
				list.addAll(info.getOrganizations());
			}
			else if(principal instanceof UsernamePasswordAuthenticationToken) {

				UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
				
				if(token.getPrincipal() instanceof SecurityInformation) {
					
					SecurityInformation info = (SecurityInformation)principal;
					list.addAll(info.getOrganizations());
				}
			}
		}
		
		log.info("Granted Organization {}", list);
		
		return list;
	}
}
