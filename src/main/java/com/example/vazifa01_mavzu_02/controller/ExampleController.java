package com.example.vazifa01_mavzu_02.controller;

import com.example.vazifa01_mavzu_02.entity.Example;
import com.example.vazifa01_mavzu_02.payload.ApiResponse;
import com.example.vazifa01_mavzu_02.payload.ExampleDto;
import com.example.vazifa01_mavzu_02.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/example")
public class ExampleController {

    @Autowired
    ExampleService exampleService;


    /**
     * add exampla
     * @param dto
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody ExampleDto dto){
        ApiResponse add = exampleService.add(dto);
        return ResponseEntity.status(add.isSuccess()?201:409).body(add);
    }

    /**
     * get exapmle
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Example>> get(){
        List<Example> examples = exampleService.get();
        return ResponseEntity.ok(examples);
    }

    /**
     * get id example
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Example> getId(@PathVariable Integer id){
        Example id1 = exampleService.getId(id);
        return ResponseEntity.ok(id1);
    }


    /**
     * edit example
     * @param id
     * @param dto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> put(@PathVariable Integer id,@Valid @RequestBody ExampleDto dto){
        ApiResponse put = exampleService.put(id, dto);
        return  ResponseEntity.status(put.isSuccess()?201:409).body(put);
    }

    /**
     * delet example
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delet(@PathVariable Integer id){
        ApiResponse delet = exampleService.delet(id);
        return ResponseEntity.status(delet.isSuccess()?201:409).body(delet);
    }
    
}
