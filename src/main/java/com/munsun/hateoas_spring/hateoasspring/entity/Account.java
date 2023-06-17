package com.munsun.hateoas_spring.hateoasspring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;
}
