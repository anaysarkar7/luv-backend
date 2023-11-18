package com.luv.urlshortener.controller;

import com.luv.urlshortener.dao.request.LoginRequest;
import com.luv.urlshortener.dao.request.SignUpRequest;
import com.luv.urlshortener.dao.response.BaseResponse;
import com.luv.urlshortener.dao.response.LoginResponse;
import com.luv.urlshortener.dao.response.SignUpResponse;
import com.luv.urlshortener.exception.UserAlreadyPresentException;
import com.luv.urlshortener.exception.UserNotFoundException;
import com.luv.urlshortener.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.luv.urlshortener.constant.MiscellaneousConstants.API_VERSION;

@Slf4j
@RestController
@RequestMapping(API_VERSION + "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public BaseResponse<?> signup(
            @RequestBody @Validated SignUpRequest signUpRequestBody
    ) {
        try {
            SignUpResponse signUpResponse = authService.signUp(signUpRequestBody);
            return BaseResponse.success(signUpResponse);
        } catch (Exception e) {//TODO: Better Exception handling
            System.out.println(e);
            System.out.println(e.getStackTrace());
            return BaseResponse.failure(e);
        }
    }

    @GetMapping("/login")
    public BaseResponse<?> login(
            @RequestBody LoginRequest loginRequestBody
    ) {
        try {
            LoginResponse loginResponse = authService.login(loginRequestBody);
            return BaseResponse.success(loginResponse);
        } catch (UserNotFoundException e) {
            System.out.println(e);
            System.out.println(e.getStackTrace());
            return BaseResponse.failure(e);
        }
    }


}
