package com.example.demo.repo;

import com.example.demo.models.Analyzes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalyzesRepo extends JpaRepository<Analyzes,Long> {
    Iterable<Analyzes> findAllByUsername(String username);
}
