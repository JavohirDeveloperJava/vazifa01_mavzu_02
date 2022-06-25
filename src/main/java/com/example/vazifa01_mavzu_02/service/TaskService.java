package com.example.vazifa01_mavzu_02.service;

import com.example.vazifa01_mavzu_02.entity.Language;
import com.example.vazifa01_mavzu_02.entity.Task;
import com.example.vazifa01_mavzu_02.payload.ApiResponse;
import com.example.vazifa01_mavzu_02.payload.TaskDto;
import com.example.vazifa01_mavzu_02.repository.LanguageRepository;
import com.example.vazifa01_mavzu_02.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    LanguageRepository languageRepository;


    /**
     * task add
     * @param dto
     * @return
     */
    public ApiResponse add(TaskDto dto){
        Optional<Language> optionalLanguage = languageRepository.findById(dto.getLanguageId());
        if (!optionalLanguage.isPresent()){
            return new ApiResponse("Bunday language yoq",false);
        }
        Task task=new Task();
        task.setName(dto.getName());
        task.setText(dto.getText());
        task.setHasStar(dto.getHasStar());
        task.setHint(dto.getHint());
        task.setSolition(dto.getSolition());
        task.setLanguageId(optionalLanguage.get());
        taskRepository.save(task);
        return new ApiResponse("task saqlandi",true);
    }

    /**
     * get task
     * @return
     */
    public List<Task> get(){
        return taskRepository.findAll();
    }


    /**
     * get id task
     * @param id
     * @return
     */
    public Task getId(Integer id){

        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()){
            return optionalTask.get();
        }
        return null;

    }


    /**
     * put task
     * @param id
     * @param dto
     * @return
     */
    public ApiResponse put(Integer id,TaskDto dto){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()){
            return new ApiResponse("bunday task mavjut emas",false);
        }
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent()){
            return new ApiResponse("bunday language mavjut emas",false);
        }
        Task task = optionalTask.get();
        task.setLanguageId(optionalLanguage.get());
        task.setHint(dto.getHint());
        task.setSolition(dto.getSolition());
        task.setName(dto.getName());
        task.setText(dto.getText());
        task.setHasStar(dto.getHasStar());
        taskRepository.save(task);
        return new ApiResponse("Task ozgartirildi",true);
    }


    /**
     * delet task
     * @param id
     * @return
     */
    public ApiResponse delet(Integer id){
        try {
            taskRepository.deleteById(id);
            return new ApiResponse("Task ochirildi",true);
        }catch (Exception e){
            return new ApiResponse("Hatolik ochmadi",false);
        }
    }
}
