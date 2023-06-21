package com.boardapp.boardapi.user.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.boardapp.boardapi.user.entity.User;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<User> findAllUsers() {
        String sql = "SELECT A.*, B.user_address, B.address_zipcode ";
        sql += "FROM users.user A ";
        sql += "LEFT OUTER JOIN users.address B ";
        sql += "ON A.user_id = B.user_id";

        return this.jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public User findUserById(String id) {
        String sql = "SELECT A.*, B.user_address, B.address_zipcode ";
        sql += "FROM users.user A ";
        sql += "LEFT OUTER JOIN users.address B ";
        sql += "ON A.user_id = B.user_id ";
        sql += "WHERE A.user_id = ?";

        return this.jdbcTemplate.queryForObject(sql, userRowMapper, id);
    }

    @Override
    public int saveUser(User user) {
        String userSql = "INSERT INTO users.user(";
        userSql += "user_id, user_name, user_password, user_tel, created_date";
        userSql += ") VALUES (?, ?, ?, ?, ?)";

        String addressSql = "INSERT INTO users.address(";
        addressSql += "user_id, user_address, user_zipcode";
        addressSql += ") VALUES (?, ?, ?)";

        int setFlag = 0;

        setFlag = this.jdbcTemplate.update(userSql, user.getUserId(), user.getUserName(),
                user.getUserPassword(), user.getUserPhoneNumber(), LocalDateTime.now());

        setFlag = this.jdbcTemplate.update(addressSql, user.getUserId(), user.getUserAddress(),
                user.getAddressZipCode());

        return setFlag;
    }

    @Override
    public int editUser(String id, User user) {
        String sql = "UPDATE users.address A SET ";
        sql += "A.user_address = ?,";
        sql += "A.address_zipcode = ? ";
        sql += "WHERE A.user_id = ?";

        return jdbcTemplate.update(sql, user.getUserAddress(), user.getAddressZipCode());
    }

    @Override
    public int deleteUser(String id) {
        return jdbcTemplate.update("DELETE FROM users.user WHERE user_id = ?", id);
    }

    private final RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
        User user = User.builder().index(resultSet.getLong("id")).id(resultSet.getString("user_id"))
                .name(resultSet.getString("user_name"))
                .password(resultSet.getString("user_password"))
                .phoneNumber(resultSet.getString("user_tel"))
                .createdDate(resultSet.getTimestamp("created_date"))
                .modifiedDate(resultSet.getTimestamp("modified_date"))
                .address(resultSet.getString("user_address"))
                .zipCode(resultSet.getString("address_zipcode")).build();

        return user;
    };
}
