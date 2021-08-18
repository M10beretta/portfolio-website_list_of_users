package com.mber.list_of_users.controller;

import com.mber.list_of_users.entity.User;
import com.mber.list_of_users.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    private final UserService service;

    public MainController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getUsers(Model model) {
        List<User> users = service.getUsers();
        model.addAttribute("users", users);
        return "main";
    }

    @PostMapping(value = "/edit", params = "edit=BLOCK")
    private String block(@RequestParam(value = "selected", required = false) Long[] selected) {
        if (selected!=null)
        for (Long selectedId : selected) {
            User user = service.getUserById(selectedId);
            user.setStatus("Blocked");
            user.setLocked(true);
            service.updateUser(user);
        }
        return "redirect:/";
    }

    @PostMapping(value = "/edit", params = "edit=UNLOCK")
    private String unblock(@RequestParam(value = "selected", required = false) Long[] selected) {
        if (selected!=null)
            for (Long selectedId : selected) {
                User user = service.getUserById(selectedId);
                user.setStatus("Unlocked");
                user.setLocked(false);
                service.updateUser(user);
            }
        return "redirect:/";
    }

    @PostMapping(value = "/edit", params = "edit=DELETE")
    private String delete(@RequestParam(value = "selected", required = false) Long[] selected) {
        if (selected!=null)
        for (Long selectedId : selected) service.deleteUser(selectedId);
        return "redirect:/";
    }
}
