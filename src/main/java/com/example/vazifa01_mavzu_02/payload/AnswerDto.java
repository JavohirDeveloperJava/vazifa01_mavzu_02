package com.example.vazifa01_mavzu_02.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    private String text;
    private Integer taskId;
    private Integer userId;
    private boolean isCorrect;
}
