package com.mber.list_of_users.appuser;

import com.mber.list_of_users.entity.User;
import com.mber.list_of_users.service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private final UserService service;

    LoginListener(UserService service) {
        this.service = service;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        UserDetails ud = (UserDetails)event.getAuthentication().getPrincipal();
        String username = ud.getUsername();
        User userByUsername = service.getUserByUsername(username);
        userByUsername.setLog(LocalDateTime.now());
        service.updateUser(userByUsername);
    }
}