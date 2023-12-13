package com.example.demo.repo.redactor;

import com.example.demo.models.redactor.Ultrasound;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UltrasoundRepo extends JpaRepository<Ultrasound, Long> {
}
