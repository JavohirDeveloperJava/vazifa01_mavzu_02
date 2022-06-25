package com.example.vazifa01_mavzu_02.controller;

import com.example.vazifa01_mavzu_02.entity.Answer;
import com.example.vazifa01_mavzu_02.payload.AnswerDto;
import com.example.vazifa01_mavzu_02.payload.ApiResponse;
import com.example.vazifa01_mavzu_02.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;


    /**
     * add answer
     * @param dto
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody AnswerDto dto){
        ApiResponse add = answerService.add(dto);
        return ResponseEntity.status(add.isSuccess()?201:409).body(add);
    }

    /**
     * get answer
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Answer>> get(){
        List<Answer> answers = answerService.get();
        return ResponseEntity.ok(answers);
    }

    /**
     * get answer id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Answer> getId(@PathVariable Integer id){
        Answer id1 = answerService.getId(id);
        return ResponseEntity.ok(id1);
    }


    /**
     * edit answer
     * @param id
     * @param dto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> put(@PathVariable Integer id,@Valid @RequestBody AnswerDto dto){
        ApiResponse put = answerService.put(id, dto);
        return ResponseEntity.status(put.isSuccess()?201:409).body(put);
    }


    /**
     * delet answer
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delet(@PathVariable Integer id){
        ApiResponse delet = answerService.delet(id);
        return ResponseEntity.status(delet.isSuccess()?201:409).body(delet);
    }


}
