package com.kratonsolution.belian.tengkawang.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.kratonsolution.belian.tengkawang.model.Organization;
import com.kratonsolution.belian.tengkawang.model.Role;
import com.kratonsolution.belian.tengkawang.model.User;
import com.kratonsolution.belian.tengkawang.service.OrganizationService;
import com.kratonsolution.belian.tengkawang.service.RoleService;
import com.kratonsolution.belian.tengkawang.service.UserService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @since 1.0
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthenticationService implements UserDetailsService
{
    @Autowired
    private UserService service;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private OrganizationService organizationService;
    
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public UserDetails loadUserByUsername(@NonNull String name) throws UsernameNotFoundException
    {
    	Optional<User> opt = service.getByName(name);
    	
        Preconditions.checkState(opt.isPresent(), "User does not exist");
        Preconditions.checkState(!Strings.isNullOrEmpty(opt.get().getRole()), "User has no role");
        
        Optional<Role> role = roleService.getByName(opt.get().getRole());
        Preconditions.checkState(role.isPresent(), "user role is disabled or does not exist");

        log.debug("User Role {}", role.get().getName());
        
        List<Authority> authoritys = new ArrayList<>();
        authoritys.add(new Authority(role.get().getName()));
        
        role.get().getGrantedAccess().stream().forEach(m->{

        	if(m.isCreate()) {
        		authoritys.add(new Authority("ROLE_"+m.getMenu().getName().replace(" ", "_").toUpperCase()+"_CREATE"));
        	}
        	
        	if(m.isRead()) {
        		authoritys.add(new Authority("ROLE_"+m.getMenu().getName().replace(" ", "_").toUpperCase()+"_READ"));
        	}
        	
        	if(m.isUpdate()) {
        		authoritys.add(new Authority("ROLE_"+m.getMenu().getName().replace(" ", "_").toUpperCase()+"_UPDATE"));
        	}
        	
        	if(m.isDelete()) {
        		authoritys.add(new Authority("ROLE_"+m.getMenu().getName().replace(" ", "_").toUpperCase()+"_DELETE"));
        	}
        	
        	if(m.isPrint()) {
        		authoritys.add(new Authority("ROLE_"+m.getMenu().getName().replace(" ", "_").toUpperCase()+"_PRINT"));
        	}
        });
        
        log.debug("Authoritys {}", authoritys);
        
        List<String> organizations = new ArrayList<>();
        organizations.add(Organization.DEFAULT);
        organizations.add(opt.get().getOrganization());
        
        organizationService.getAllTree(opt.get().getOrganization(), organizations);
        
        return new SecurityInformation(opt.get(), authoritys, organizations);
    }
}