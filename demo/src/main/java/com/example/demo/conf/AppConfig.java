package com.example.demo.conf;

import com.example.demo.desingpatterns.factory.CartaFactory;
import com.example.demo.desingpatterns.factory.DefaultCardFactory;
import com.example.demo.desingpatterns.factory.GreenCardFactory;
import com.example.demo.desingpatterns.factory.RedCardFactory;
import com.example.demo.utils.OutputUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "com.example.demo")
public class AppConfig {

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    @Bean
    public CartaFactory cartaFactory(){
        OutputUtils.start();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n Now choose an interface for the cards \n\n[1] Red \n[2] Green");
        int colorInterface = scanner.nextInt();

        return switch (colorInterface) {
            case 1 -> new RedCardFactory();
            case 2 -> new GreenCardFactory();
            default -> new DefaultCardFactory();
        };
    }


}
