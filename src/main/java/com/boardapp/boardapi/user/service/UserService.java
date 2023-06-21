package com.boardapp.boardapi.user.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.boardapp.boardapi.user.entity.User;
import com.boardapp.boardapi.user.model.UserDto;
import com.boardapp.boardapi.user.repository.JdbcUserRepository;

@Service
public class UserService {
    private final JdbcUserRepository jdbcUserRepository;

    public UserService(JdbcUserRepository jdbcUserRepository) {
        this.jdbcUserRepository = jdbcUserRepository;
    }

    public List<UserDto> getAllUser() {
        List<User> userList = this.jdbcUserRepository.findAllUsers();

        if (userList.isEmpty()) {
            return null;
        }

        List<UserDto> userDtoList = new ArrayList<UserDto>();

        for (User user : userList) {
            UserDto userDto = UserDto.builder().index(user.getUserIndex()).id(user.getUserId())
                    .name(user.getUserName()).password(user.getUserPassword())
                    .phoneNumber(user.getUserPhoneNumber()).address(user.getUserAddress())
                    .zipCode(user.getAddressZipCode()).createdDate(user.getCreatedDate())
                    .modifiedDate(user.getModifiedDate()).build();

            userDtoList.add(userDto);
        }

        return userDtoList;
    }

    public UserDto getUserById(String userId) {
        User user = this.jdbcUserRepository.findUserById(userId);

        if (user == null) {
            return null;
        }

        UserDto userDto = UserDto.builder().index(user.getUserIndex()).id(user.getUserId())
                .name(user.getUserName()).password(user.getUserPassword())
                .phoneNumber(user.getUserPhoneNumber()).address(user.getUserAddress())
                .zipCode(user.getAddressZipCode()).createdDate(user.getCreatedDate())
                .modifiedDate(user.getModifiedDate()).build();

        return userDto;
    }

    public int saveUser(UserDto userDto) {
        return this.jdbcUserRepository.saveUser(userDto.toEntity());
    }

    public int modifyUser(String userId, UserDto userDto) {
        return this.jdbcUserRepository.editUser(userId, userDto.toEntity());
    }

    public int deleteUser(String userId) {
        return this.jdbcUserRepository.deleteUser(userId);
    }
}
