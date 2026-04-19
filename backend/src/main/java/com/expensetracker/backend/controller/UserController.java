package com.expensetracker.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.expensetracker.backend.entities.AppUser;
import com.expensetracker.backend.services.AppUserService;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.backend.dto.UserDto;
import com.expensetracker.backend.mapper.UserMapper;


@RestController
@Validated
@RequestMapping("/api/users")
public class UserController {

    //Abhängigkeit zu UserService und UserMapper
    private final AppUserService userService;
    private final UserMapper userMapper;
    
    public UserController(AppUserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    //Abrufen aller Benutzer
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
    //Abrufen eines Benutzers nach ID
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable @NonNull Long id) {
        return userMapper.toDto(userService.getUserById(id));
    }
    //Erstellen eines neuen Benutzers
    @PostMapping
    @SuppressWarnings("null")
    public UserDto createUser(@RequestBody @Validated @NonNull UserDto userDto) {
        AppUser appUser = userMapper.toEntity(userDto);
        return userMapper.toDto(userService.createUser(appUser));
    }
    //Löschen eines Benutzers
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable @NonNull Long id) {
        userService.deleteUser(id);
    }
    //Aktualisieren eines Benutzers
    @PatchMapping("/{id}")
    @SuppressWarnings("null")
    public UserDto updateUser(@PathVariable @NonNull Long id, @RequestBody @Validated @NonNull UserDto userDto) {
        AppUser appUser = userMapper.toEntity(userDto);
        return userMapper.toDto(userService.updateUser(id, appUser));
    }
    

    
    
}
