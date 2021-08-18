package com.mber.list_of_users.service;

import com.mber.list_of_users.entity.User;
import com.mber.list_of_users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public List<User> saveUsers(List<User> user) {
        return repository.saveAll(user);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public void updateUser(User user) {
        User UserT = repository.findById(user.getId()).orElse(null);
        Objects.requireNonNull(UserT).setUsername(user.getUsername());
        UserT.setEmail(user.getEmail());
        UserT.setPassword(user.getPassword());
        UserT.setAppUserRole(user.getAppUserRole());
        UserT.setLocked(user.getLocked());
        UserT.setStatus(user.getStatus());
        UserT.setReg(user.getReg());
        UserT.setLog(user.getLog());
        repository.save(UserT);
    }
}
