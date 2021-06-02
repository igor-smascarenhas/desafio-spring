package com.digitalhouse.desafiospring.controllers;

import com.digitalhouse.desafiospring.dtos.PostDTO;
import com.digitalhouse.desafiospring.entities.Post;
import com.digitalhouse.desafiospring.services.PostService;
import com.digitalhouse.desafiospring.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public ResponseEntity<PostDTO> newPost(@RequestBody Post post) {

        PostDTO postDTO = postService.save(post);

        return ResponseEntity.ok().body(postDTO);

    }

    @RequestMapping(value = "/followed/{userId}/list", method = RequestMethod.GET)
    public ResponseEntity<List<PostDTO>> getPostsByUserId(@PathVariable("userId") Long userId) {

        List<PostDTO> postDTOS = postService.findPostByUserId(userId);

        return ResponseEntity.ok().body(postDTOS);

    }

}
