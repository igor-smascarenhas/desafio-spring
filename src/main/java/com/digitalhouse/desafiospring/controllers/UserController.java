package com.digitalhouse.desafiospring.controllers;

import com.digitalhouse.desafiospring.dtos.UserDTO;
import com.digitalhouse.desafiospring.services.UserService;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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

    @RequestMapping(value = "/{userId}/unfollow/{userIdToUnfollow}", method = RequestMethod.POST)
    public ResponseEntity unfollow(@PathVariable("userId") Long userId, @PathVariable("userIdToUnfollow") Long userIdToUnfollow) {
        try {
            userService.unfollowSeller(userId, userIdToUnfollow);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{userId}/following/list", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getCustomerFollowingList(@PathVariable("userId") Long userId, @RequestParam(value = "order", required = false) String order) {

        UserDTO userDTO = null;
        List<UserDTO> userDTOS = null;

        try {

            userDTO = userService.findById(userId);
            userDTOS = userService.orderUsersByName(userDTO.getFollowing(), order);

        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(userDTOS);
    }

}
