/**
 * 
 */
package com.mystyle.user.mangement.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author chandan
 *
 */

public interface UserDetailsService {

	/**
	 * @param username
	 * @return
	 * @throws UserNameNotFoundException
	 */
	
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
