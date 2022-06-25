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
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @NotNull(message = "text bow bolmasligi kk")
    @Column(nullable = false)
    private String text;

    @NotNull(message = "taskId bow bolmasligi kk")
    @ManyToOne(optional = false)
    private Task taskId;
}
