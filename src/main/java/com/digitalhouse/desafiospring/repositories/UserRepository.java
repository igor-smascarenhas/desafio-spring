package com.digitalhouse.desafiospring.repositories;

import com.digitalhouse.desafiospring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
