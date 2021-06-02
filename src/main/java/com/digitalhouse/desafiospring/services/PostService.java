package com.digitalhouse.desafiospring.services;

import com.digitalhouse.desafiospring.dtos.PostDTO;
import com.digitalhouse.desafiospring.entities.Post;
import com.digitalhouse.desafiospring.repositories.PostRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void findById(Long id) throws NotFoundException {
        Optional<Post> postOptional = postRepository.findById(id);

        if(!postOptional.isPresent()) {
            throw new NotFoundException("Post with id " + id + " not found");
        }

    }

    public List<PostDTO> findPostByUserId(Long id) {
        List<PostDTO> postDTOS = postRepository.findPostsByUserId(id).get().stream().map(post -> new PostDTO(post)).collect(Collectors.toList());

        return postDTOS;
    }

    public PostDTO save(Post post) {
        post.setDate(new Date());
       Post newPost = postRepository.save(post);
       return new PostDTO(newPost);
    }


}
