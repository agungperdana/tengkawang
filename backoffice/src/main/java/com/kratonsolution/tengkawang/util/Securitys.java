package com.kratonsolution.tengkawang.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.kratonsolution.tengkawang.backend.model.Role;
import com.kratonsolution.tengkawang.backoffice.auth.SecurityInformation;

import lombok.NonNull;
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

	public static final boolean isRoot(@NonNull Authentication auth) {
		return auth.getAuthorities().stream().anyMatch(p->p.getAuthority().equals(Role.ROOT));
	}

	public static String getOrganization(@NonNull Object principal) {

		if(principal != null) {

			if(principal instanceof SecurityInformation) {

				SecurityInformation info = (SecurityInformation)principal;
				return info.getUser().getOrganization();
			}
			else if(principal instanceof UsernamePasswordAuthenticationToken) {

				UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;

				if(token.getPrincipal() instanceof SecurityInformation) {

					SecurityInformation info = (SecurityInformation)principal;
					return info.getUser().getOrganization();
				}
			}
		}
		
		return null;
	}
}
