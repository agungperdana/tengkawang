package com.kratonsolution.tengkawang.backoffice.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.base.Preconditions;
import com.kratonsolution.tengkawang.backend.model.User;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Getter
@Setter
public class SecurityInformation implements UserDetails
{
	private static final long serialVersionUID = 1L;

	private User user;
	
	private Collection<Authority> authoritys;
	
	private List<String> organizations;
	
    private String token;
	
	public SecurityInformation(@NonNull User user, @NonNull Collection<Authority> authoritys, @NonNull List<String> organizations)
	{
		this.user = user;
		this.authoritys = authoritys;
		this.organizations = organizations;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<Authority> getAuthorities()
	{
		return authoritys;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword()
	{
		return user.getPassword();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername()
	{
		return user.getName();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled()
	{
		return true;
	}
	
	public void clearPassword() {
	    
	    Preconditions.checkState(user != null, "User does not exist");
	    user.setPassword(null);
	}
}
