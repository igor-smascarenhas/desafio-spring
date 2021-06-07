package com.digitalhouse.desafiospring.controllers;

import com.digitalhouse.desafiospring.dtos.SellerDTO;
import com.digitalhouse.desafiospring.dtos.UserDTO;
import com.digitalhouse.desafiospring.services.SellerService;
import com.digitalhouse.desafiospring.services.UserService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class SellerController {

    private SellerService sellerService;
    private UserService userService;

    public SellerController(SellerService sellerService, UserService userService) {
        this.sellerService = sellerService;
        this.userService = userService;
    }

    @RequestMapping(value = "/sellers", method = RequestMethod.GET)
    public ResponseEntity<List<SellerDTO>> findAll() {
        List<SellerDTO> sellersDTOS = sellerService.findAll();
        return ResponseEntity.ok().body(sellersDTOS);
    }

    @RequestMapping(value = "/sellers/{sellerId}", method = RequestMethod.GET)
    public ResponseEntity<SellerDTO> findById(@PathVariable("sellerId") Long sellerId) {
        SellerDTO sellerDTO = sellerService.findById(sellerId);
        return ResponseEntity.ok().body(sellerDTO);
    }

    @RequestMapping(value = "/{userId}/followers/count/", method = RequestMethod.GET)
    public ResponseEntity<SellerDTO> getSellerCount(@PathVariable("userId") Long userId) {

        SellerDTO sellerDTO = null;
        try {
            sellerDTO = sellerService.getSellerCount(userId);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(sellerDTO);
    }

    @RequestMapping(value = "/{userId}/followers/list", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getSellerFollowers(@PathVariable("userId") Long userId, @RequestParam(value = "order", required = false) String order) {

        SellerDTO sellerDTO = null;
        List<UserDTO> userDTOS = null;

        try {
            sellerDTO = sellerService.getSellerFollowers(userId);
            userDTOS = userService.orderUsersByName(sellerDTO.getFollowers(), order);

        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(userDTOS);
    }

}
