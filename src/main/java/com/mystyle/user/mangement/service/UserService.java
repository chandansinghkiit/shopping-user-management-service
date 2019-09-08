package com.mystyle.user.mangement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mystyle.user.mangement.model.User;

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
