package com.project.newsfeed.utils;

import com.auth0.jwt.JWT;

public class RequestUtils {

    /**
     * Method gets username of the requester from the request header
     *
     * @param token
     * @return
     */
    public static String getRequesterUsername(String token) {

        token = token.substring("Bearer".length()).trim();
        String requesterUsername = JWT.decode(token).getClaim("username").asString();
        return requesterUsername;
    }
}
