package com.kratonsolution.belian.tengkawang.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.kratonsolution.belian.tengkawang.model.User;
import com.kratonsolution.belian.tengkawang.repository.UserRepository;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @since 1.0
 */
@Slf4j
@Service
public class AuthenticationService implements UserDetailsService
{
    @Autowired
    private UserRepository service;
    
    @Override
    public UserDetails loadUserByUsername(@NonNull String name) throws UsernameNotFoundException
    {
    	Optional<User> userOpt = service.findOneByName(name);
        
        Preconditions.checkState(userOpt.isPresent(), "User does not exist");
        
        List<Authority> list = new ArrayList<>();
        
        log.info("Authorized for {}", list);
        
        return new SecurityInformation(userOpt.get(), new ArrayList<>());
    }
}
