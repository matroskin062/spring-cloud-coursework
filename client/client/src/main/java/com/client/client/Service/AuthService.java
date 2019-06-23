package com.client.client.Service;


import com.client.client.Model.Users;
import org.springframework.data.repository.CrudRepository;


public interface AuthService extends CrudRepository<Users, Integer> {
    Users findByUsername(String username);
}
