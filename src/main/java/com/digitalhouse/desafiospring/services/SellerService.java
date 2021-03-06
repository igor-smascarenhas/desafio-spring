package com.digitalhouse.desafiospring.services;

import com.digitalhouse.desafiospring.dtos.SellerDTO;
import com.digitalhouse.desafiospring.entities.Seller;
import com.digitalhouse.desafiospring.repositories.UserRepository;
import com.digitalhouse.desafiospring.repositories.SellerRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerService {

    private SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List<SellerDTO> findAll() {
        List<Seller> sellers = sellerRepository.findAll();
        List<SellerDTO> sellerDTOS = sellers.stream().map(seller -> new SellerDTO(seller)).collect(Collectors.toList());

        return sellerDTOS;
    }

    public SellerDTO findById(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId).get();

        return new SellerDTO(seller);
    }

    public SellerDTO getSellerCount(Long sellerId) throws NotFoundException {

        Optional<Seller> sellerOptional = sellerRepository.findById(sellerId);

        if(!sellerOptional.isPresent()) {
            throw new NotFoundException("Seller with id: "+ sellerId + " not found!");
        }

        SellerDTO sellerDTO = new SellerDTO(sellerOptional.get());
        sellerDTO.setFollowers(null);

        return sellerDTO;
    }

    public SellerDTO getSellerFollowers(Long sellerId) throws NotFoundException {
        Optional<Seller> sellerOptional = sellerRepository.findById(sellerId);

        if(!sellerOptional.isPresent()) {
            throw new NotFoundException("Seller with id: "+ sellerId + " not found!");
        }

        SellerDTO sellerDTO = new SellerDTO(sellerOptional.get());
        sellerDTO.setFollowersCount(null);

        return sellerDTO;
    }


}
