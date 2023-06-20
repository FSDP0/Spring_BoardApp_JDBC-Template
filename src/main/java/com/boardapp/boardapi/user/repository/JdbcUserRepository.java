package com.boardapp.boardapi.user.repository;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.boardapp.boardapi.user.entity.User;

@Repository
public class JdbcUserRepository implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query("SELECT * FROM users.user",
                (rs, rowNum) -> new User(rs.getString("user_id"), rs.getString("user_name"),
                        rs.getString("user_password"), rs.getString("user_phonenumber"),
                        rs.getString("user_address"), rs.getString("address_zipcode"),
                        rs.getTimestamp("created_date"), rs.getTimestamp("modified_date")));
    }

    @Override
    public void findUserById(String id) {
        // return jdbcTemplate.queryForObject("SELECT * FROM users.user WHERE user_id = ?",
        // new Object[] {id}, (rs,rowNum)-> new);
    }

    @Override
    public int saveUser(User user) {
        return jdbcTemplate.update("INSERT INTO users.user VALUES (?,?,?,?,?)", user.getUserId(),
                user.getUserName(), user.getUserPassword(), user.getUserPhoneNumber(),
                user.getUserAddress(), user.getAddressZipCode());
    }

    @Override
    public int editUser(String id, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editUser'");
    }

    @Override
    public int deleteUser(String id) {
        return jdbcTemplate.update("DELETE FROM users.user WHERE user_id = ?", id);
    }

}
