package com.digitalhouse.desafiospring.repositories;

import com.digitalhouse.desafiospring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
