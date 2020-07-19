package com.insight.crescendo.domain;


import lombok.Data;

@Data
public class changePasswordUrl {
    private String id;
    private String password;
    private String newpassword;
}
