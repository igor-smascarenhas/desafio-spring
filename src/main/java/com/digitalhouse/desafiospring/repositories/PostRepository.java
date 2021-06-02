package com.digitalhouse.desafiospring.repositories;

import com.digitalhouse.desafiospring.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<List<Post>> findPostsByUserId(Long userId);

}
