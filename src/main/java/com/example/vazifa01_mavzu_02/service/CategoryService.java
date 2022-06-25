package com.example.vazifa01_mavzu_02.service;

import com.example.vazifa01_mavzu_02.entity.Category;
import com.example.vazifa01_mavzu_02.entity.Language;
import com.example.vazifa01_mavzu_02.payload.ApiResponse;
import com.example.vazifa01_mavzu_02.payload.CategoryDto;
import com.example.vazifa01_mavzu_02.repository.CategoryRepository;
import com.example.vazifa01_mavzu_02.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LanguageRepository languageRepository;

    public ApiResponse add(CategoryDto dto){
        Optional<Language> optionalLanguage = languageRepository.findById(dto.getLanguageID());
        if (!optionalLanguage.isPresent()){
            return new ApiResponse("Bunday language mavjut emas",false);
        }
        Category category=new Category();
        category.setName(dto.getName());
        category.setCondition(dto.getCondition());
        category.setLanguages(optionalLanguage.get());
        categoryRepository.save(category);
        return new ApiResponse("Category saqlandi",true);
    }


    /**
     * get category
     * @return
     */
    public List<Category> get(){
        List<Category> all = categoryRepository.findAll();
        return all;
    }


    /**
     *get category id
     * @param id
     * @return
     */
    public Category getId(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            return category;
        }
        return null;
    }


    /**
     * put category
     * @param id
     * @param dto
     * @return
     */
    public ApiResponse put(Integer id, CategoryDto dto){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return new ApiResponse("Bunday category mavjut emas",false);
        }
        Optional<Language> optionalLanguage = languageRepository.findById(dto.getLanguageID());
        if (!optionalLanguage.isPresent()){
            return new ApiResponse("Bunday language mavjut emas",false);
        }
        Category category = optionalCategory.get();
        category.setCondition(dto.getCondition());
        category.setName(dto.getName());
        category.setLanguages(optionalLanguage.get());
        categoryRepository.save(category);
        return new ApiResponse("category ozgardi" ,true);
    }


    /**
     * delet category
     * @param id
     * @return
     */
    public ApiResponse delet(Integer id){
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Category ochirildi",true);
        }catch (Exception e){
            return new ApiResponse("Categoryda hatolik",false);
        }
    }
}
