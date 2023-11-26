package com.example.demo.repo;

import com.example.demo.models.Support;
import org.springframework.data.repository.CrudRepository;

public interface SupportRepo extends CrudRepository<Support,Long> {
}
