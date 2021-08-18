package com.mber.list_of_users.controller;

import com.mber.list_of_users.appuser.AppUserRole;
import com.mber.list_of_users.entity.User;
import com.mber.list_of_users.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;


@Controller
@RequestMapping("/registration")
public class RegistrationController {
    final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser(User user, Model model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            String userExist = "User \"" + userFromDb.getUsername() + "\" already exists";
            model.addAttribute("user_exist", userExist);
            return "registration";
        }
        user.setLocked(false);
        user.setStatus("Unlocked");
        user.setAppUserRole(AppUserRole.USER);
        user.setReg(LocalDateTime.now());
        userRepository.save(user);
        return "login";
    }
}




