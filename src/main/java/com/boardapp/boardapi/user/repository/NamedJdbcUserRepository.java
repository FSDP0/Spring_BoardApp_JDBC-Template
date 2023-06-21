package com.boardapp.boardapi.user.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.boardapp.boardapi.user.entity.User;

@Repository
public class NamedJdbcUserRepository implements UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // Auto generate RowMapper for User
    private final RowMapper<User> userRowMapper = BeanPropertyRowMapper.newInstance(User.class);

    public NamedJdbcUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<User> findAllUsers() {
        String SELECT_ALL = "SELECT A.*, B.user_address, B.address_zipcode ";
        SELECT_ALL += "FROM users.user A ";
        SELECT_ALL += "LEFT OUTER JOIN users.address B ";
        SELECT_ALL += "ON A.user_id = B.user_id";

        return this.namedParameterJdbcTemplate.query(SELECT_ALL, userRowMapper);
    }

    @Override
    public User findUserById(String userId) {
        String SELECT_BY_USER_ID = "SELECT A.*, B.user_address, B.address_zipcode ";
        SELECT_BY_USER_ID += "FROM users.user A ";
        SELECT_BY_USER_ID += "LEFT OUTER JOIN users.address B ";
        SELECT_BY_USER_ID += "ON A.user_id = B.user_id ";
        SELECT_BY_USER_ID += "WHERE A.user_id = :user_id";

        SqlParameterSource namedParameter = new MapSqlParameterSource("user_id", userId);

        return this.namedParameterJdbcTemplate.queryForObject(SELECT_BY_USER_ID, namedParameter,
                userRowMapper);
    }

    @Override
    public int saveUser(User user) {
        String INSERT_USER = "INSERT INTO users.user(";
        INSERT_USER += "user_id, user_name, user_password, user_tel, created_date";
        INSERT_USER += ") VALUES (";
        INSERT_USER += ":userId, :userName, :userPassword, :userPhoneNumber, :createdDate";
        INSERT_USER += ")";

        String INSERT_ADDRESS = "INSERT INTO users.address(";
        INSERT_ADDRESS += "user_id, user_address, address_zipcode";
        INSERT_ADDRESS += ") VALUES (:userId, :userAddress, :addressZipCode)";

        user.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));

        // SQL query return effected row count;
        int effectFlag = 0;

        SqlParameterSource userNamedParameterSource = new BeanPropertySqlParameterSource(user);
        effectFlag = this.namedParameterJdbcTemplate.update(INSERT_USER, userNamedParameterSource);

        SqlParameterSource addressNamedParameterSource = new BeanPropertySqlParameterSource(user);
        effectFlag =
                this.namedParameterJdbcTemplate.update(INSERT_ADDRESS, addressNamedParameterSource);

        return effectFlag;
    }

    @Override
    public int editUser(String id, User user) {
        String UPDATE_USER_BY_USER_ID = "UPDATE users.user SET ";
        UPDATE_USER_BY_USER_ID += "user_name = :userName,";
        UPDATE_USER_BY_USER_ID += "user_password = :userPassword, ";
        UPDATE_USER_BY_USER_ID += "user_tel = :userPhoneNumber, ";
        UPDATE_USER_BY_USER_ID += "modified_date = :modifiedDate ";
        UPDATE_USER_BY_USER_ID += "WHERE user_id = :userId";

        String UPDATE_ADDRESS_BY_USER_ID = "UPDATE users.address SET ";
        UPDATE_ADDRESS_BY_USER_ID += "user_address = :userAddress, ";
        UPDATE_ADDRESS_BY_USER_ID += "address_zipcode = :addressZipCode ";
        UPDATE_ADDRESS_BY_USER_ID += "WHERE user_id = :userId";

        // Add values to null fields
        user.setUserId(id);
        user.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

        // SQL query return effected row count;
        int effectFlag = 0;

        // SQL Parameter Injection (User Table) from argument object data
        SqlParameterSource userNamedParameterSource = new BeanPropertySqlParameterSource(user);
        effectFlag = this.namedParameterJdbcTemplate.update(UPDATE_USER_BY_USER_ID,
                userNamedParameterSource);

        // SQL Parameter Injection (Address Table) from argument object data
        SqlParameterSource addressNamedParameterSource = new BeanPropertySqlParameterSource(user);
        effectFlag = this.namedParameterJdbcTemplate.update(UPDATE_ADDRESS_BY_USER_ID,
                addressNamedParameterSource);

        return effectFlag;
    }

    @Override
    public int deleteUser(String id) {
        String DELETE_BY_USER_ID = "DELETE FROM users.user WHERE user_id = :user_id";

        SqlParameterSource namedParameter = new MapSqlParameterSource("user_id", id);

        return this.namedParameterJdbcTemplate.update(DELETE_BY_USER_ID, namedParameter);
    }
}
