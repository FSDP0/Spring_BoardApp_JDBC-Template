package com.boardapp.boardapi.user.repository;

import java.util.List;
import com.boardapp.boardapi.user.entity.User;

public interface UserRepository {
    List<User> findAllUsers();

    User findUserById(String id);

    int saveUser(User user);

    int editUser(String id, User user);

    int deleteUser(String id);
}
