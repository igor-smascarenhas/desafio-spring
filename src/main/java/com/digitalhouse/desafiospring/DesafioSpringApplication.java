package com.digitalhouse.desafiospring;

import com.digitalhouse.desafiospring.entities.Seller;
import com.digitalhouse.desafiospring.entities.User;
import com.digitalhouse.desafiospring.repositories.UserRepository;
import com.digitalhouse.desafiospring.repositories.SellerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DesafioSpringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DesafioSpringApplication.class, args);
    }

    private final UserRepository customerRepository;
    private final SellerRepository sellerRepository;

    public DesafioSpringApplication(UserRepository customerRepository, SellerRepository sellerRepository) {
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User c1 = new User();
        c1.setUsername("Igor");

        User c2 = new User();
        c2.setUsername("Amanda");

        Seller s1 = new Seller();
        s1.setUsername("Lucas");

        Seller s2 = new Seller();
        s2.setUsername("Bruna");

        Seller s3 = new Seller();
        s3.setUsername("Alan");

        c1.follow(s1);
        c2.follow(s1);
        c2.follow(s2);
        c2.follow(s3);
        s1.addFollower(c1);
        s1.addFollower(c2);
        s2.addFollower(c2);
        s3.addFollower(c2);

        customerRepository.saveAll(Arrays.asList(c1, c2));
        sellerRepository.saveAll(Arrays.asList(s1, s2, s3));

    }
}
