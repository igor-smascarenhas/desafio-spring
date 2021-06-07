package com.digitalhouse.desafiospring.services;

import com.digitalhouse.desafiospring.dtos.UserDTO;
import com.digitalhouse.desafiospring.entities.Seller;
import com.digitalhouse.desafiospring.entities.User;
import com.digitalhouse.desafiospring.repositories.UserRepository;
import com.digitalhouse.desafiospring.repositories.SellerRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private SellerRepository sellerRepository;

    public UserService(UserRepository userRepository, SellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
    }

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();

        List<UserDTO> userDTOS = users.stream().map(user -> new UserDTO(user.getId(), user.getUsername())).collect(Collectors.toList());

        return userDTOS;
    }

    public UserDTO findById(Long userId) throws NotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);

        if(!userOptional.isPresent()) {
            throw new NotFoundException("User not found");
        }

        UserDTO userDTO = new UserDTO(userOptional.get());

        return userDTO;
    }

    public void followSeller(Long userId, Long sellerId) throws NotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Seller> sellerOptional = sellerRepository.findById(sellerId);

        if(!userOptional.isPresent() || !sellerOptional.isPresent()) {
            throw new NotFoundException("User or Seller invalid");
        }

        User user = userOptional.get();
        Seller seller = sellerOptional.get();

        if(isAlreadyFollowing(user, sellerId)) {
            return;
        }

        user.follow(seller);
        seller.addFollower(user);

        userRepository.save(user);
        sellerRepository.save(seller);
    }

    public void unfollowSeller(Long userId, Long sellerId) throws NotFoundException {

        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Seller> sellerOptional = sellerRepository.findById(sellerId);

        if(!userOptional.isPresent() || !sellerOptional.isPresent()) {
            throw new NotFoundException("User or Seller invalid");
        }

        User user = userOptional.get();
        Seller seller = sellerOptional.get();

        if(!isAlreadyFollowing(user, sellerId)) {
            return;
        }

        user.unfollow(seller);
        seller.removeFollower(user);

        userRepository.save(user);
        sellerRepository.save(seller);
    }

    private boolean isAlreadyFollowing(User user, Long sellerId) {
       Optional<Seller> sellerOptional = user.getFollowing().stream().filter(seller -> seller.getId() == sellerId).findFirst();

       return sellerOptional.isPresent();
    }

    public List<UserDTO> orderUsersByName(List<UserDTO> userDTOS, String order) {

        if(order == null) {
            return userDTOS;
        }

        if(order.contains("name")) {
            if(order.contains("asc")) {
                Collections.sort(userDTOS);
            } else if(order.contains("desc")) {
                Collections.sort(userDTOS, Collections.reverseOrder());
            }
        }

        return userDTOS;

    }

}
