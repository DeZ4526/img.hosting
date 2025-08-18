package com.sservice.img.hosting.service;


import com.sservice.img.hosting.dto.UserData;
import com.sservice.img.hosting.dto.UserInfo;
import com.sservice.img.hosting.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserData> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserInfo> getUserList() {
        var users = userRepository.findAll();
        List<UserInfo> result = new ArrayList<>();
        for (int i = 0; i < users.stream().count(); i++){
            result.add(new UserInfo(users.get(i).getUsername(), users.get(i).getRole()));
        }
        return result;
    }


}
