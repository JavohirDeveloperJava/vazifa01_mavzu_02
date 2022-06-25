package com.example.vazifa01_mavzu_02.controller;

import com.example.vazifa01_mavzu_02.entity.Task;
import com.example.vazifa01_mavzu_02.payload.ApiResponse;
import com.example.vazifa01_mavzu_02.payload.TaskDto;
import com.example.vazifa01_mavzu_02.service.TaskService;
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
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;


    /**
     * add task
     * @param dto
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody TaskDto dto){
        ApiResponse add = taskService.add(dto);
        return ResponseEntity.status(add.isSuccess()?201:409).body(add);
    }


    /**
     * get task
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Task>> get(){
        List<Task> tasks = taskService.get();
        return ResponseEntity.ok(tasks);
    }


    /**
     * get Id task
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getId(@PathVariable Integer id){
        Task id1 = taskService.getId(id);
        return ResponseEntity.ok(id1);
    }

    /**
     * put task
     * @param id
     * @param dto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> put(@PathVariable Integer id,@Valid @RequestBody TaskDto dto){
        ApiResponse put = taskService.put(id, dto);
        return ResponseEntity.status(put.isSuccess()?201:409).body(put);
    }

    /**
     * delet task
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delet(@PathVariable Integer id){
        ApiResponse delet = taskService.delet(id);
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
