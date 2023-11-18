package com.luv.urlshortener.service;

import com.luv.urlshortener.config.security.JwtService;
import com.luv.urlshortener.dao.UserServiceDao;
import com.luv.urlshortener.dao.request.LoginRequest;
import com.luv.urlshortener.dao.request.SignUpRequest;
import com.luv.urlshortener.dao.response.LoginResponse;
import com.luv.urlshortener.dao.response.SignUpResponse;
import com.luv.urlshortener.enums.Role;
import com.luv.urlshortener.enums.TokenType;
import com.luv.urlshortener.exception.UserAlreadyPresentException;
import com.luv.urlshortener.exception.UserNotFoundException;
import com.luv.urlshortener.model.Token;
import com.luv.urlshortener.model.User;
import com.luv.urlshortener.repository.TokenRepository;
import com.luv.urlshortener.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest loginRequest) throws UserNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        if (userService.isUserPresent(loginRequest.getEmail())) {
            User user = userService.getUser(loginRequest.getEmail());
            if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                String accessToken = jwtService.generateToken(user);
                return LoginResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken("TODO")// TODO: Implement Refresh Token
                        .build();
            } else throw new BadCredentialsException("Incorrect Password!");
        } else throw new UserNotFoundException();
    }

    //TODO: Generate token during signup for direct login
    //TODO: Check SQL relation warning log
    public SignUpResponse signUp(SignUpRequest signUpRequest) throws UserAlreadyPresentException {
        if (!userService.isUserPresent(signUpRequest.getEmail())) {
            User newUser = User.builder()
                    .role(Role.USER)// TODO: Default role set as USER during signup
                    .email(signUpRequest.getEmail())
                    .password(passwordEncoder.encode(signUpRequest.getRawPassword()))
                    .firstname(signUpRequest.getFirstname())
                    .lastname(signUpRequest.getLastname())
                    .build();

            User createdUser = userRepository.save(newUser);
            return SignUpResponse.builder()
                    .userId(createdUser.getUserId())
                    .build();
        } else
            throw new UserAlreadyPresentException("User with email: " + signUpRequest.getEmail() + " is already present!");
    }

}
