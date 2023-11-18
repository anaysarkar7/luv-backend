package com.luv.urlshortener.constant;

import static com.luv.urlshortener.constant.MiscellaneousConstants.API_VERSION;

public class AuthConstants {
    public static final String[] AUTH_IGNORED_PATHS = {
//            "/test",
            API_VERSION + "/auth/login",
            API_VERSION + "/auth/signup"

    };

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;//5hrs in milliseconds

}
