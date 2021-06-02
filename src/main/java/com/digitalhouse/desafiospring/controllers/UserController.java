package com.digitalhouse.desafiospring.controllers;

import com.digitalhouse.desafiospring.dtos.UserDTO;
import com.digitalhouse.desafiospring.services.UserService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @RequestMapping(value = "/{userId}/follow/{userIdToFollow}", method = RequestMethod.POST)
    public ResponseEntity follow(@PathVariable("userId") Long userId, @PathVariable("userIdToFollow") Long userIdToFollow) {
        try {
            userService.followSeller(userId, userIdToFollow);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{userId}/following/list", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getCustomerFollowingList(@PathVariable("userId") Long userId) {
        UserDTO userDTO = null;

        try {
            userDTO = userService.findById(userId);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(userDTO);
    }

}