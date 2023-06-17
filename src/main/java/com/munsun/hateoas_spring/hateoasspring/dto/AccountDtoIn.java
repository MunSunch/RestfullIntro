package com.munsun.hateoas_spring.hateoasspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountDtoIn {
    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;
}
