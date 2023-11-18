package com.luv.urlshortener.service;

import com.luv.urlshortener.dao.UserServiceDao;
import com.luv.urlshortener.dao.request.PasswordResetRequest;
import com.luv.urlshortener.model.User;
import com.luv.urlshortener.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepositoryInstance;
    public boolean isUserPresent(String email) {
        User userObj = userRepositoryInstance
                .findByEmail(email)
                .orElse(null);
        return userObj != null;
    }

    public User getUser(String email) {
        return  userRepositoryInstance.findByEmail(email).orElse(null);
    }

    public void passwordReset(PasswordResetRequest passwordResetRequest, Principal connectedUser) {
    }

}
