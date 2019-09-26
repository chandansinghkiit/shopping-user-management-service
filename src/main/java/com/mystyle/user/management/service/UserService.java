package com.mystyle.user.management.service;
import java.util.List;

import com.mystyle.user.management.model.User;

public interface UserService {
    User save(User user);

    User findByUsername(String username);

    List<String> findUsers(List<Long> idList);
}