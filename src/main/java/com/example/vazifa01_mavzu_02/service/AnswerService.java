package com.example.vazifa01_mavzu_02.service;

import com.example.vazifa01_mavzu_02.entity.Answer;
import com.example.vazifa01_mavzu_02.entity.Task;
import com.example.vazifa01_mavzu_02.entity.User;
import com.example.vazifa01_mavzu_02.payload.AnswerDto;
import com.example.vazifa01_mavzu_02.payload.ApiResponse;
import com.example.vazifa01_mavzu_02.repository.AnswerRepositroy;
import com.example.vazifa01_mavzu_02.repository.TaskRepository;
import com.example.vazifa01_mavzu_02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepositroy answerRepositroy;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;


    /**
     * add answer
     * @param dto
     * @return
     */
    public ApiResponse add(AnswerDto dto){
        Optional<Task> optionalTask = taskRepository.findById(dto.getTaskId());
        if (!optionalTask.isPresent()){
            return new ApiResponse("Bunday task mavjud emas",false);
        }
        Optional<User> optionalUser = userRepository.findById(dto.getTaskId());
        if (!optionalUser.isPresent()){
            return new ApiResponse("Bunday user mavjut emas",false);
        }
        Answer answer=new Answer();
        answer.setTaskId(optionalTask.get());
        answer.setCorrect(dto.isCorrect());
        answer.setText(dto.getText());
        answer.setUserId(optionalUser.get());
        answerRepositroy.save(answer);
        return new ApiResponse("Answer saqlandi",true);
    }

    /**
     * get answer
     * @return
     */
    public List<Answer> get(){
        return answerRepositroy.findAll();
    }

    /**
     * get answer id
     * @param id
     * @return
     */
    public Answer getId(Integer id){
        Optional<Answer> optionalAnswer = answerRepositroy.findById(id);
        return optionalAnswer.orElse(null);
    }

    /**
     * edit answer
     * @param id
     * @param dto
     * @return
     */
    public ApiResponse put(Integer id, AnswerDto dto){
        Optional<Answer> optionalAnswer = answerRepositroy.findById(id);
        if (!optionalAnswer.isPresent()){
            return new ApiResponse("Bunday answer mavjut emas", false);
        }
        Optional<User> optionalUser = userRepository.findById(dto.getUserId());
        if (!optionalUser.isPresent()){
            return new ApiResponse("Bunday user mavjut emas", false);
        }
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()){
            return new ApiResponse("Bunday task mavjut emas", false);
        }
        Answer answer = optionalAnswer.get();
        answer.setUserId(optionalUser.get());
        answer.setCorrect(dto.isCorrect());
        answer.setText(dto.getText());
        answer.setTaskId(optionalTask.get());
        answerRepositroy.save(answer);
        return new ApiResponse("Answer ozgardi",true);
    }

    /**
     * delet answer
     * @param id
     * @return
     */
    public ApiResponse delet(Integer id){
        try {
            answerRepositroy.deleteById(id);
            return new ApiResponse("Answer ochirildi",true);
        }catch (Exception e){
            return new ApiResponse("Hatolik ochmadi",false);
        }
    }
}
