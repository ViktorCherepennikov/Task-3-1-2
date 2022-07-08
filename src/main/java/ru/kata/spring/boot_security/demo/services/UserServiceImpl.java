package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    @Transactional
    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    @Override
    @Transactional
    public List<User> findAll() {return userRepository.findAll();}
    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }
    @Override
    @Transactional
    public void  deleteById(Long id){
        userRepository.deleteById(id);
    }
}
