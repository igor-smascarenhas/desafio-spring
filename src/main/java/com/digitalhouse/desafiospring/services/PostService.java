package com.digitalhouse.desafiospring.services;

import com.digitalhouse.desafiospring.dtos.PostDTO;
import com.digitalhouse.desafiospring.dtos.PromoPostDTO;
import com.digitalhouse.desafiospring.dtos.UserDTO;
import com.digitalhouse.desafiospring.entities.Post;
import com.digitalhouse.desafiospring.entities.User;
import com.digitalhouse.desafiospring.repositories.PostRepository;
import com.digitalhouse.desafiospring.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void findById(Long id) throws NotFoundException {
        Optional<Post> postOptional = postRepository.findById(id);

        if(!postOptional.isPresent()) {
            throw new NotFoundException("Post with id " + id + " not found");
        }

    }

    public List<PostDTO> findPostByUserId(Long id) throws NotFoundException {

        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()) {
            throw new NotFoundException("User with id " + id + " not found");
        }

        List<PostDTO> postDTOS = postRepository.findPostsByUserId(id).get().stream().map(post -> new PromoPostDTO(post)).collect(Collectors.toList());

        return postDTOS;
    }

    public PostDTO newPost(Post post) {
        post.setDate(new Date());
        Post newPost = postRepository.save(post);
        return new PostDTO(newPost);
    }

    public PromoPostDTO newPromoPost(Post post) {
        post.setDate(new Date());
        Post newPromoPost = postRepository.save(post);
        return new PromoPostDTO(newPromoPost);
    }

    public List<PromoPostDTO> findPromoPostsByUserId(Long userId) throws NotFoundException {

        Optional<User> userOptional = userRepository.findById(userId);

        if(!userOptional.isPresent()) {
            throw new NotFoundException("User with id " + userId + " not found");
        }

        List<Post> posts = postRepository.findPostsByUserId(userId).get();
        List<PromoPostDTO> promoPostDTOS = null;

        if(posts.size() > 0) {
            promoPostDTOS = posts.stream()
                    .filter(post -> post.getHasPromo() != null)
                    .map(post -> new PromoPostDTO(post)).collect(Collectors.toList());
        }

        return promoPostDTOS;

    }

    public List<PostDTO> orderPostsDate(List<PostDTO> postDTOS, String order) {

        if(order == null) {
            return postDTOS;
        }

        if(order.contains("date")) {
            if(order.contains("asc")) {
                Collections.sort(postDTOS);
            } else if(order.contains("desc")) {
                Collections.sort(postDTOS, Collections.reverseOrder());
            }
        }

        return postDTOS;
    }


}
