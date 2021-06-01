package com.digitalhouse.desafiospring;

import com.digitalhouse.desafiospring.entities.Customer;
import com.digitalhouse.desafiospring.entities.Seller;
import com.digitalhouse.desafiospring.repositories.CustomerRepository;
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

    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;

    public DesafioSpringApplication(CustomerRepository customerRepository, SellerRepository sellerRepository) {
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Customer c1 = new Customer();
        c1.setUsername("Igor");

        Customer c2 = new Customer();
        c2.setUsername("Amanda");

        Seller s1 = new Seller();
        s1.setUsername("Lucas");

        Seller s2 = new Seller();
        s2.setUsername("Bruna");

        c1.follow(s1);
        s1.addFollower(c1);

        customerRepository.saveAll(Arrays.asList(c1, c2));
        sellerRepository.saveAll(Arrays.asList(s1, s2));

    }
}
