package com.example.demo.utils;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@AllArgsConstructor
public class InputUtils {

    @Autowired
    private final Scanner scanner;

    public int getInterfaceConf(){
        System.out.println("\n\n Now choose an interface for the cards \n\n[1] Red \n[2] Green");
        return scanner.nextInt();
    }

    public String getName(){
        System.out.println("Please type a your name: ");
        return scanner.nextLine();
    }

    public String getAnotherCar(){
        System.out.println("\nDo you want another card [y] or [any] otherwise");
        return scanner.nextLine();
    }
}
