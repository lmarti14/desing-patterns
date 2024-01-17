package com.example.demo.service;

import com.example.demo.desingpatterns.factory.CartaFactory;
import com.example.demo.repository.CardRepo;
import com.example.demo.repository.entity.Card;
import com.example.demo.service.interfaces.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardServiceImp implements CardService {


    private final CardRepo cardRepo;


    private final CartaFactory cartaFactory;

    public Card getCard(int id){
        return cardRepo.findById(id).orElseThrow();
    }

    @Override
    public String getFormatCard(int choice) {
        Card card = getCard(choice);
        return cartaFactory.getCard(card);
    }

}
