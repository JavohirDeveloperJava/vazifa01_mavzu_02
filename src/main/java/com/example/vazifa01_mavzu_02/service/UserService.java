package com.example.vazifa01_mavzu_02.service;

import com.example.vazifa01_mavzu_02.entity.User;
import com.example.vazifa01_mavzu_02.payload.ApiResponse;
import com.example.vazifa01_mavzu_02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * user add
     * @param user
     * @return
     */
    public ApiResponse add(User user){
        boolean exists = userRepository.existsByEmail(user.getEmail());
        if (exists){
            return new ApiResponse("Bunday user mavjut",false);
        }
        User user1=new User();
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
        return new ApiResponse("User saqlandi",true);
    }


    /**
     * get all user
     * @return
     */
    public List<User> get(){
        return userRepository.findAll();
    }


    /**
     * userni id orqli olish
     * @param id
     * @return
     */
    public User getId(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            return user;
        }
        return null;
    }

    public ApiResponse put(Integer id, User user){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            return new ApiResponse("Bunday user mavjut emas",false);
        }
        boolean exists = userRepository.existsByEmail(user.getEmail());
        if (exists) {
            return new ApiResponse("Bunday email mavjut ",false);
        }
        User user1 = optionalUser.get();
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
        return new ApiResponse("User ozgardi",true);
    }


    /**
     * userni ochirish
     * @param id
     * @return
     */
    public ApiResponse delet(Integer id){
       try {
           userRepository.deleteById(id);
           return new ApiResponse("User ochdi",true);
       }catch (Exception e){
           return new ApiResponse("Hatolik ochmadi",false);
       }
    }
}
