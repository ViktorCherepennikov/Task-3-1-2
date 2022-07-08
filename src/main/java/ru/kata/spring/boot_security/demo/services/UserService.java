package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    User findById(Long id);

    List<User> findAll();

    void saveUser(User user);
    void  deleteById(Long id);
}
