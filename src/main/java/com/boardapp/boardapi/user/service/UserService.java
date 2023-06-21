package com.boardapp.boardapi.user.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.boardapp.boardapi.user.entity.User;
import com.boardapp.boardapi.user.model.UserDto;
import com.boardapp.boardapi.user.repository.JdbcUserRepository;
import com.boardapp.boardapi.user.repository.NamedJdbcUserRepository;

@Service
public class UserService {
    private final JdbcUserRepository jdbcUserRepository;
    private final NamedJdbcUserRepository namedJdbcUserRepository;

    public UserService(JdbcUserRepository jdbcUserRepository,
            NamedJdbcUserRepository namedJdbcUserRepository) {
        this.jdbcUserRepository = jdbcUserRepository;
        this.namedJdbcUserRepository = namedJdbcUserRepository;
    }

    public List<UserDto> getAllUser() {
        // List<User> userList = this.jdbcUserRepository.findAllUsers();
        List<User> userList = this.namedJdbcUserRepository.findAllUsers();

        if (userList.isEmpty()) {
            return null;
        }

        List<UserDto> userDtoList = new ArrayList<UserDto>();

        for (User user : userList) {
            UserDto userDto = UserDto.builder().index(user.getId()).id(user.getUserId())
                    .name(user.getUserName()).password(user.getUserPassword())
                    .phoneNumber(user.getUserTel()).address(user.getUserAddress())
                    .zipCode(user.getAddressZipcode()).createdDate(user.getCreatedDate())
                    .modifiedDate(user.getModifiedDate()).build();

            userDtoList.add(userDto);
        }

        return userDtoList;
    }

    public UserDto getUserById(String userId) {
        // User user = this.jdbcUserRepository.findUserById(userId);
        User user = this.namedJdbcUserRepository.findUserById(userId);

        if (user == null) {
            return null;
        }

        UserDto userDto = UserDto.builder().index(user.getId()).id(user.getUserId())
                .name(user.getUserName()).password(user.getUserPassword())
                .phoneNumber(user.getUserTel()).address(user.getUserAddress())
                .zipCode(user.getAddressZipcode()).createdDate(user.getCreatedDate())
                .modifiedDate(user.getModifiedDate()).build();

        return userDto;
    }

    public int saveUser(UserDto userDto) {
        // return this.jdbcUserRepository.saveUser(userDto.toEntity());
        return this.namedJdbcUserRepository.saveUser(userDto.toEntity());
    }

    public int modifyUser(String userId, UserDto userDto) {
        // return this.jdbcUserRepository.editUser(userId, userDto.toEntity());
        return this.namedJdbcUserRepository.editUser(userId, userDto.toEntity());
    }

    public int deleteUser(String userId) {
        // return this.jdbcUserRepository.deleteUser(userId);
        return this.namedJdbcUserRepository.deleteUser(userId);
    }
}
