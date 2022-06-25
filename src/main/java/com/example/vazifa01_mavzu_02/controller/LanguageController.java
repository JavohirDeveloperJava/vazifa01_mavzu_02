package com.example.vazifa01_mavzu_02.controller;

import com.example.vazifa01_mavzu_02.entity.Language;
import com.example.vazifa01_mavzu_02.payload.ApiResponse;
import com.example.vazifa01_mavzu_02.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lan")
public class LanguageController {

    @Autowired
    LanguageService languageService;


    /**
     * Language add
     * @param language
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody Language language){
        ApiResponse add = languageService.add(language);
        return ResponseEntity.status(add.isSuccess()?201:409).body(add);
    }


    /**
     * all get language
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Language>> get(){
        List<Language> languages = languageService.get();
        return ResponseEntity.ok(languages);
    }


    /**
     * get id language
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Language> getId(@Valid @PathVariable Integer id){
        Language id1 = languageService.getId(id);
        return ResponseEntity.ok(id1);
    }


    /**
     * language edit
     * @param id
     * @param language
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse>  put(@PathVariable Integer id,@Valid @RequestBody Language language){
        ApiResponse put = languageService.put(id, language);
        return ResponseEntity.status(put.isSuccess()?201:409).body(put);
    }


    /**
     * language deleted
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delet(@PathVariable Integer id){
        ApiResponse delet = languageService.delet(id);
        return ResponseEntity.status(delet.isSuccess()?201:409).body(delet);
    }


    //////////////////////////////////////////////////////////////////////


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
