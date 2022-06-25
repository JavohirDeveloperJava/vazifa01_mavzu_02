package com.example.vazifa01_mavzu_02.controller;

import com.example.vazifa01_mavzu_02.entity.Category;
import com.example.vazifa01_mavzu_02.payload.ApiResponse;
import com.example.vazifa01_mavzu_02.payload.CategoryDto;
import com.example.vazifa01_mavzu_02.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    /**
     * add categor
     * @param dto
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody CategoryDto dto){
        ApiResponse add = categoryService.add(dto);
        return ResponseEntity.status(add.isSuccess()?201:409).body(add);
    }

    /**
     * get list category
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Category>> get(){
        List<Category> categories = categoryService.get();
        return ResponseEntity.ok(categories);
    }


    /**
     * get category id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getId(@PathVariable Integer id){
        Category id1 = categoryService.getId(id);
        return ResponseEntity.ok(id1);
    }


    /**
     * edit category
     * @param id
     * @param dto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> put(@PathVariable Integer id,@Valid @RequestBody CategoryDto dto){
        ApiResponse put = categoryService.put(id, dto);
        return ResponseEntity.status(put.isSuccess()?201:409).body(put);
    }


    /**
     * delet category
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delet(@PathVariable Integer id){
        ApiResponse delet = categoryService.delet(id);
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
