package com.example.vazifa01_mavzu_02.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String name;
    private String text;
    private String solition;
    private String hint;
    private String hasStar;
    private Integer languageId;
}
