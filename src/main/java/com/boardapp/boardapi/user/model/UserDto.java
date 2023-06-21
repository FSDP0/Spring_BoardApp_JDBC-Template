package com.boardapp.boardapi.user.model;

import java.util.Date;
import com.boardapp.boardapi.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
    private Long index;
    private String id;
    private String name;
    private String password;
    private String phoneNumber;
    private String address;
    private String zipCode;
    private Date createdDate;
    private Date modifiedDate;

    public User toEntity() {
        User user = User.builder().id(this.id).name(this.name).password(this.password)
                .phoneNumber(this.phoneNumber).address(this.address).zipCode(this.zipCode).build();

        return user;
    }

    @Builder
    public UserDto(Long index, String id, String name, String password, String phoneNumber,
            String address, String zipCode, Date createdDate, Date modifiedDate) {
        this.index = index;
        this.id = id;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.zipCode = zipCode;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
