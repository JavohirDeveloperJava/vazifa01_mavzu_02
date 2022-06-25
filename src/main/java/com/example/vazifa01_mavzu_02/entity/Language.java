package com.example.vazifa01_mavzu_02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @NotNull(message = "name bow bolmasligi kk")
    @Column(nullable = false)
    private String name;

}
