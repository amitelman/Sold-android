package com.mid.mid;

public class FlavorConfig {

    public static final String BASE = "https://www.middiamonds.com/";
    public static final String BASE_LOGIN = BASE + "login/";
    public static final String BASE_TOKEN = BASE_LOGIN + "core/connect/token/";
    public static final String SERVER_URL = "https://mobileapi2.midonline.com/mobilefeapi";
    public static final String SCOPES = "openid profile roles mobile_api_2_frontend mobile_api_user_info offline_access";
    public static final String TOKEN_SCOPES = "openid profile roles mobile_api_2_frontend mobile_api_user_info offline_access";
    public static final String CLIENT_ID = "mobileapi2frontend";
    public static final String CLIENT_SECRET = "midsecret";
    public static final String RESPONSE_TYPE = "token code id_token";
    public static final String CHALLENGE_METHOD = "S256";
    public static final String CHALLENGE_CODE = "qjrzSW9gMiUgpUvqgEPE4_-8swvyCtfOVvg55o5S_es";
    public static final String STATE = "L8gsc22iu7kDgcMPqLQlA8MMZAQWYdNOXNS1QSyMkts";
    public static final String AUTHORIZE = BASE_LOGIN + "core/connect/authorize";
    public static final String TOKEN = BASE_LOGIN + "core/connect/token";
    public static final String REDIRECT_URL = "com.mid.app:/oauth2redirect/provider";


}