package com.topyougo.productimport.constant;

public final class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long   EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/register";
    public static final String LOGIN_URL = "/api/login";
    public static final String LOGOUT_URL = "/api/logout";
    public static final String HEALTH_CHECK = "/";
    public static final String SHOPIFY_WEBHOOK = "/api/store/shopifyWebhook";
	public static final String[] AUTH_WHITELIST = {"/v3/api-docs/**","/v3/api-docs.yaml","/swagger-ui/**","/swagger-ui.html"}; // -- Swagger UI v3 (OpenAPI)
}