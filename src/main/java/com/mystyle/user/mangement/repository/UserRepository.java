package com.mystyle.user.mangement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mystyle.user.mangement.model.User;
/**
 * 
 * @author chandan
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	 Optional<User> findByUsername(String username);
	 
	  @Query("select u.name from User u where u.id in (:pIdList)")
	    List<String> findByIdList(@Param("pIdList") List<Long> idList);

}
