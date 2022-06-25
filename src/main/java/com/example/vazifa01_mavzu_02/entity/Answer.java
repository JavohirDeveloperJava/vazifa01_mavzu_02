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
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @NotNull(message = "text bow bolmasligi kk")
    @Column(nullable = false)
    private String text;

    @NotNull(message = "isCorrect bow bolmasligi kk")
    @OneToOne(optional = false)
    private Task taskId;

    @NotNull(message = "isCorrect bow bolmasligi kk")
    @OneToOne(optional = false)
    private User userId;

    @NotNull(message = "isCorrect bow bolmasligi kk")
    private boolean isCorrect;
}
