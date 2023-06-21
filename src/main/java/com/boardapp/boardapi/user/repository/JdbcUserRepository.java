package com.boardapp.boardapi.user.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.boardapp.boardapi.user.entity.User;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = BeanPropertyRowMapper.newInstance(User.class);

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
        addressSql += "user_id, user_address, address_zipcode";
        addressSql += ") VALUES (?, ?, ?)";

        int effectFlag = 0;

        effectFlag = this.jdbcTemplate.update(userSql, user.getUserId(), user.getUserName(),
                user.getUserPassword(), user.getUserTel(), LocalDateTime.now());

        effectFlag = this.jdbcTemplate.update(addressSql, user.getUserId(), user.getUserAddress(),
                user.getAddressZipcode());

        return effectFlag;
    }

    @Override
    public int editUser(String userId, User user) {
        String userSql = "UPDATE users.user SET ";
        userSql += "user_name = ?,";
        userSql += "user_password = ?, ";
        userSql += "user_tel = ?, ";
        userSql += "modified_date = ? ";
        userSql += "WHERE user_id = ?";

        String addressSql = "UPDATE users.address SET ";
        addressSql += "user_address = ?, ";
        addressSql += "address_zipcode = ? ";
        addressSql += "WHERE user_id = ?";

        int effectFlag = 0;

        effectFlag = this.jdbcTemplate.update(userSql, user.getUserName(), user.getUserPassword(),
                user.getUserTel(), LocalDateTime.now(), userId);


        effectFlag = this.jdbcTemplate.update(addressSql, user.getUserAddress(),
                user.getAddressZipcode(), userId);

        return effectFlag;
    }

    @Override
    public int deleteUser(String userId) {
        String sql = "DELETE FROM users.user WHERE user_id = ?";

        return this.jdbcTemplate.update(sql, userId);
    }

    // Create RowMapper with @Builder
    // private final RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
    // User user = User.builder().index(resultSet.getLong("id")).id(resultSet.getString("user_id"))
    // .name(resultSet.getString("user_name"))
    // .password(resultSet.getString("user_password"))
    // .phoneNumber(resultSet.getString("user_tel"))
    // .createdDate(resultSet.getTimestamp("created_date"))
    // .modifiedDate(resultSet.getTimestamp("modified_date"))
    // .address(resultSet.getString("user_address"))
    // .zipCode(resultSet.getString("address_zipcode")).build();

    // return user;
    // };
}
