package com.example.vazifa01_mavzu_02.payload;

import com.example.vazifa01_mavzu_02.entity.Language;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private String name;
    private String condition; // holati
    private Integer languageID;
}
