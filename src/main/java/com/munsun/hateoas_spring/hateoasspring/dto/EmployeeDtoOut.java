package com.munsun.hateoas_spring.hateoasspring.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class EmployeeDtoOut {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("account")
    private AccountDtoOut account;

    @JsonSetter
    public void addAccount(String login, String password) {
        account.setLogin(login);
        account.setPassword(password);
    }
}
