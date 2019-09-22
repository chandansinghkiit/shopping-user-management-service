package com.mystyle.user.management.service;

import java.util.List;

import com.mystyle.user.management.model.User;

/**
 * 
 * @author chandan
 *
 */

public interface UserService {

	/**
	 * @param user
	 * @return
	 */
	User save(User user);

	/**
	 * @param username
	 * @return
	 */
	User findByUsername(String username);

	/**
	 * @param idList
	 * @return
	 */
	List<String> findUsers(List<Long> idList);

}
