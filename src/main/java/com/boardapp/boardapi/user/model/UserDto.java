package com.boardapp.boardapi.user.model;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
    private String id;
    private String name;
    private String password;
    private String phoneNumber;
    private String address;
    private String zipCode;
    private Date createdDate;
    private Date modifiedDate;

    @Builder
    public UserDto(String id, String name, String password, String phoneNumber, String address,
            String zipCode, Date createdDate, Date modifiedDate) {
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
