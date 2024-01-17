package com.example.demo.repository;

import com.example.demo.repository.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoundRepo extends JpaRepository<Round, Integer> {
}
