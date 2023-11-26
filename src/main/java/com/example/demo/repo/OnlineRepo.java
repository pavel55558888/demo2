package com.example.demo.repo;

import com.example.demo.models.Online;
import org.springframework.data.repository.CrudRepository;

public interface OnlineRepo extends CrudRepository<Online,Long> {
}
