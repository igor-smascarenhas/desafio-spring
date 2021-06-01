package com.digitalhouse.desafiospring.repositories;

import com.digitalhouse.desafiospring.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
