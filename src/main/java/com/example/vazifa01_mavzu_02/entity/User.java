package com.example.vazifa01_mavzu_02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @NotNull(message = "email bow bolmasligi kk")
    @Column(nullable = false,unique = true)
    private String email;

    @NotNull(message = "password bow bolmasligi kk")
    @Column(nullable = false)
    private String password;
}
