package com.ditraacademy.travelagency.core.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignInRequest {
    private String email;
    private String password;
}
