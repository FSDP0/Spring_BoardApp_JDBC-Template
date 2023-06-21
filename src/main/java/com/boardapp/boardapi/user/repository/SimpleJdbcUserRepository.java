package com.boardapp.boardapi.user.repository;

import java.util.List;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import com.boardapp.boardapi.user.entity.User;

@Repository
public class SimpleJdbcUserRepository implements UserRepository {

    // private final SimpleJdbcInsert simpleJdbcInsert;

    // public SimpleJdbcUserRepository(SimpleJdbcInsert simpleJdbcInsert) {
    // this.simpleJdbcInsert = simpleJdbcInsert;
    // }

    @Override
    public List<User> findAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllUsers'");
    }

    @Override
    public User findUserById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findUserById'");
    }

    @Override
    public int saveUser(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'saveUser'");
    }

    @Override
    public int editUser(String id, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editUser'");
    }

    @Override
    public int deleteUser(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

}
