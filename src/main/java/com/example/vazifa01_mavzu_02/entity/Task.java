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
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @NotNull(message = "name bow bolmasligi kk")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "text bow bolmasligi kk")
    @Column(nullable = false)
    private String text;

    @NotNull(message = "solition bow bolmasligi kk")
    @Column(nullable = false)
    private String solition;

    @NotNull(message = "hint bow bolmasligi kk")
    @Column(nullable = false)
    private String hint;

    @NotNull(message = "hasStar bow bolmasligi kk")
    @Column(nullable = false)
    private String hasStar;

    @NotNull(message = "languageId bow bolmasligi kk")
    @ManyToOne(optional = false)
    private Language languageId;
}
