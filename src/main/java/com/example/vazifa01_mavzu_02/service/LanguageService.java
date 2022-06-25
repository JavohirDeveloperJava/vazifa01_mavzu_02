package com.example.vazifa01_mavzu_02.service;

import com.example.vazifa01_mavzu_02.entity.Language;
import com.example.vazifa01_mavzu_02.payload.ApiResponse;
import com.example.vazifa01_mavzu_02.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;


    /**
     * language add
     * @param language
     * @return
     */
    public ApiResponse add(Language language){
        boolean exists = languageRepository.existsByName(language.getName());
        if (exists){
            return new ApiResponse("Bunday language mavjut",false);
        }

        Language language1=new Language();
        language1.setName(language.getName());
        languageRepository.save(language1);
        return new ApiResponse("Language saqlandi",true);
    }

    /**
     * language get all
     * @return
     */
    public List<Language> get(){
        return languageRepository.findAll();
    }


    /**
     * language get id
     * @param id
     * @return
     */
    public Language getId(Integer id){
        Optional<Language> byId = languageRepository.findById(id);
        if (byId.isPresent()){
            Language language = byId.get();
            return language;
        }
        return null;
    }


    /**
     * Language edit
     * @param id
     * @param language
     * @return
     */
    public ApiResponse put(Integer id,Language language){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent()){
            return new ApiResponse("Bunday language mavjut emas",false);
        }
        boolean exists = languageRepository.existsByName(language.getName());
        if (exists){
            return new ApiResponse("Bunday language mavjut bowqa yoz",false);
        }
        Language language1 = optionalLanguage.get();
        language1.setName(language.getName());
        languageRepository.save(language1);
        return new ApiResponse("Language saqlandi",true);
    }

    public ApiResponse delet(Integer id){
        try {
            languageRepository.deleteById(id);
            return new ApiResponse("language ochdi",true);
        }catch (Exception e){
            return new ApiResponse("Hatolik ochmadi",false);
        }
    }


}
