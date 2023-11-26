package com.example.demo.repo;

import com.example.demo.models.Staff;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepo extends CrudRepository<Staff,Long> {
}
