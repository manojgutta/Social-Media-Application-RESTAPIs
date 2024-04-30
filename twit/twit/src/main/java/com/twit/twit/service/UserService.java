package com.twit.twit.service;

import com.twit.twit.model.UserClass;
import com.twit.twit.model.UserDTO;
import com.twit.twit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserClass findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserClass save(UserClass user) {
        return userRepository.save(user);
    }

    public UserClass findByuserID(int id) {
        return userRepository.findByuserID(id);
    }

    public UserDTO getUserDetails(int userID) {
        UserClass user = userRepository.findByuserID(userID);
        if (user != null) {
            return new UserDTO(user.getName(), user.getUserID(), user.getEmail());
        } else {
            return null;
        }
    }

    public List<UserDTO> getAllUsers() {
        List<UserClass> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (UserClass user : userList) {
            UserDTO userDTO = new UserDTO(user.getName(), user.getUserID(), user.getEmail());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

//    private UserDTO convertToDTO(UserClass user) {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setEmail(user.getEmail());
//        userDTO.setName(user.getName());
//        userDTO.setUserID(user.getUserID());
//        return userDTO;
//    }
}


