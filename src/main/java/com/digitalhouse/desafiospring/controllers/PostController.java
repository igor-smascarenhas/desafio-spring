package com.digitalhouse.desafiospring.controllers;

import com.digitalhouse.desafiospring.dtos.*;
import com.digitalhouse.desafiospring.entities.Post;
import com.digitalhouse.desafiospring.services.PostService;
import com.digitalhouse.desafiospring.services.UserService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public ResponseEntity<PostDTO> newPost(@RequestBody Post post) {

        PostDTO postDTO = postService.newPost(post);

        return ResponseEntity.ok().body(postDTO);

    }

    @RequestMapping(value = "/newpromopost", method = RequestMethod.POST)
    public ResponseEntity<PromoPostDTO> newPromoPost(@RequestBody Post post) {

        PromoPostDTO promoPostDTO = postService.newPromoPost(post);

        return ResponseEntity.ok().body(promoPostDTO);

    }

    @RequestMapping(value = "/followed/{userId}/list", method = RequestMethod.GET)
    public ResponseEntity<List<PostDTO>> getPostsByUserId(@PathVariable("userId") Long userId, @RequestParam(value = "order", required = false) String order) {

        Instant now = Instant.now(); //current date
        Instant initialInstant = now.minus(Duration.ofDays(14));
        Date initialDate = Date.from(initialInstant);

        List<PostDTO> postDTOS = null;
        try {
            postDTOS = postService.findPostByUserId(userId).stream().filter(postDTO -> postDTO.getDate().after(initialDate)).collect(Collectors.toList());
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        postDTOS = postService.orderPostsDate(postDTOS, order);

        return ResponseEntity.ok().body(postDTOS);

    }

    @RequestMapping(value = "/{userId}/countPromo", method = RequestMethod.GET)
    public ResponseEntity<PromoCountDTO> countPromo(@PathVariable("userId") Long userId) {

        PromoCountDTO promoPostDTO = null;

        try {
            UserDTO userDTO = userService.findById(userId);

            var promoPostDTOS = postService.findPromoPostsByUserId(userId);

            Integer promoPostsCount = promoPostDTOS != null ? promoPostDTOS.size() : 0;
            promoPostDTO = new PromoCountDTO(userId, userDTO.getUsername(), promoPostsCount);

        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok().body(promoPostDTO);
    }

    @RequestMapping(value = "/{userId}/list", method = RequestMethod.GET)
    public ResponseEntity<UserPromoPostsDTO> userPromoPosts(@PathVariable("userId") Long userId) {

        UserPromoPostsDTO userPromoPostsDTO = null;

        try {
            UserDTO userDTO = userService.findById(userId);

            var promoPostDTOS = postService.findPromoPostsByUserId(userId);


            userPromoPostsDTO = new UserPromoPostsDTO(userDTO.getUserId(), userDTO.getUsername(), promoPostDTOS);

        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok().body(userPromoPostsDTO);
    }

}
