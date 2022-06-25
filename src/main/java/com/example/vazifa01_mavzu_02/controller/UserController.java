package com.example.vazifa01_mavzu_02.controller;

import com.example.vazifa01_mavzu_02.entity.User;
import com.example.vazifa01_mavzu_02.payload.ApiResponse;
import com.example.vazifa01_mavzu_02.service.UserService;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * user add
     * @param user
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody User user){
        ApiResponse add = userService.add(user);
        return ResponseEntity.status(add.isSuccess()?201:409).body(add);
    }


    /**
     * get all user
     * @return
     */
    @GetMapping
    public ResponseEntity<List<User>> get(){
        List<User> users = userService.get();
        return ResponseEntity.ok(users);
    }


    /**
     * userni id orqalik olish
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getId(@PathVariable Integer id){
        User id1 = userService.getId(id);
        return ResponseEntity.ok(id1);
    }

    /**
     * userni edit qilish
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> put(@PathVariable Integer id,@Valid @RequestBody User user){
        ApiResponse put = userService.put(id, user);
        return ResponseEntity.status(put.isSuccess()?201:409).body(put);
    }


    /**
     * useni ochirish
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delet(@PathVariable Integer id){
        ApiResponse delet = userService.delet(id);
        return ResponseEntity.status(delet.isSuccess()?201:409).body(delet);
    }

///////////////////////////////////////////////////////////////////////////

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
