package com.example.demo.service.interfaces;

import com.example.demo.repository.entity.Card;


public interface CardService {

    public Card getCard(int id);

    public String getFormatCard(int choice);
}
