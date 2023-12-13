package com.example.demo.repo.redactor;

import com.example.demo.models.redactor.Cardiologist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardiologistRepo extends JpaRepository<Cardiologist, Long> {
}
