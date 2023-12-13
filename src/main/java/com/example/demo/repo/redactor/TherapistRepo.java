package com.example.demo.repo.redactor;

import com.example.demo.models.redactor.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TherapistRepo extends JpaRepository<Therapist,Long> {
}
