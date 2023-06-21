package com.boardapp.boardapi.user.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Default constructor setting for generate rowmapper
@NoArgsConstructor
@Setter
@Getter
public class User {
    private Long Id;

    private String userId;
    private String userName;
    private String userPassword;
    private String userTel;
    private String userAddress;
    private String addressZipcode;

    private Date createdDate;
    private Date modifiedDate;

    @Builder
    public User(Long index, String id, String name, String password, String phoneNumber,
            String address, String zipCode, Date createdDate, Date modifiedDate) {
        this.Id = index;
        this.userId = id;
        this.userName = name;
        this.userPassword = password;
        this.userTel = phoneNumber;
        this.userAddress = address;
        this.addressZipcode = zipCode;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
