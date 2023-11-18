package com.luv.urlshortener.controller;

import com.luv.urlshortener.dao.request.PasswordResetRequest;
import com.luv.urlshortener.dao.response.BaseResponse;
import com.luv.urlshortener.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.luv.urlshortener.constant.MiscellaneousConstants.API_VERSION;

@RestController
@RequestMapping(API_VERSION + "/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userServiceInstance;

    @PatchMapping("/password-reset")
    public BaseResponse<?> userPasswordReset(
            @RequestBody PasswordResetRequest passwordResetRequestBody,
            Principal connectedUser
    ) {
        userServiceInstance.passwordReset(passwordResetRequestBody, connectedUser);
        return BaseResponse.success();
    }
}
