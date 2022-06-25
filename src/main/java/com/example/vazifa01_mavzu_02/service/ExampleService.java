package com.example.vazifa01_mavzu_02.service;

import com.example.vazifa01_mavzu_02.entity.Example;
import com.example.vazifa01_mavzu_02.entity.Task;
import com.example.vazifa01_mavzu_02.payload.ApiResponse;
import com.example.vazifa01_mavzu_02.payload.ExampleDto;
import com.example.vazifa01_mavzu_02.repository.ExampleRepository;
import com.example.vazifa01_mavzu_02.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {
    @Autowired
    ExampleRepository exampleRepository;
    @Autowired
    TaskRepository taskRepository;

    /**
     * add example
     * @param dto
     * @return
     */
    public ApiResponse add(ExampleDto dto){
        Optional<Task> optionalTask = taskRepository.findById(dto.getTaskId());
        if (!optionalTask.isPresent()){
            return new ApiResponse("Bunday task mavjut emas",false);
        }
        Example example=new Example();
        example.setText(dto.getText());
        example.setTaskId(optionalTask.get());
        exampleRepository.save(example);
        return new ApiResponse("Example saqlandi" ,true);
    }

    /**
     * get example
     * @return
     */
    public List<Example> get(){
        return exampleRepository.findAll();
    }


    /**
     * get id example
     * @param id
     * @return
     */
    public Example getId(Integer id){
        Optional<Example> optionalExample = exampleRepository.findById(id);
        return optionalExample.orElse(null);
    }


    /**
     * edit example
     * @param id
     * @param dto
     * @return
     */
    public ApiResponse put(Integer id,ExampleDto dto){
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent()){
            return new ApiResponse("Bunday example mavjut emas",false);
        }
        Optional<Task> optionalTask = taskRepository.findById(dto.getTaskId());
        if (!optionalTask.isPresent()){
            return new ApiResponse("Bunday task mavjut emas",false);
        }

        Example example = optionalExample.get();
        example.setText(dto.getText());
        example.setTaskId(optionalTask.get());
        exampleRepository.save(example);
        return new ApiResponse("example ozgardi" ,true);
    }

    /**
     * delet example
     * @param id
     * @return
     */
    public ApiResponse delet(Integer id){
        try {
            exampleRepository.deleteById(id);
            return new ApiResponse("example ochdi",true);
        }catch (Exception e){
            return new ApiResponse("hatolik",false);
        }
    }
}
