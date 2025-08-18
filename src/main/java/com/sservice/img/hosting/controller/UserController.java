package com.sservice.img.hosting.controller;

import com.sservice.img.hosting.dto.UserData;
import com.sservice.img.hosting.dto.UserInfo;
import com.sservice.img.hosting.dto.UserRegisterRequest;
import com.sservice.img.hosting.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Получить всех пользователей", description = "Только для админов")
    @GetMapping("/get_all")
    public List<UserData> getUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Получить всех пользователей", description = "Список с id пользователей")
    @GetMapping("/get_user_list")
    public List<UserInfo> getUserList() {
        return userService.getUserList();
    }

}
