package com.digitalhouse.desafiospring.controllers;

import com.digitalhouse.desafiospring.dtos.SellerDTO;
import com.digitalhouse.desafiospring.services.SellerService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class SellerController {

    private SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
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
    public ResponseEntity<SellerDTO> getSellerFollowers(@PathVariable("userId") Long userId) {

        SellerDTO sellerDTO = null;
        try {
            sellerDTO = sellerService.getSellerFollowers(userId);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(sellerDTO);
    }

}
