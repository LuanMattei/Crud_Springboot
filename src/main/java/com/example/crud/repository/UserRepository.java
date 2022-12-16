package com.example.crud.repository;

import com.example.crud.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User, Long> {
    public Long countById( Long id);
}
