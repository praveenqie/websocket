package com.example.websocket.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class HttpHandshakeInterceptor implements HandshakeInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(HttpHandshakeInterceptor.class);
    private final JwtTokenUtil jwtTokenUtil;

    public HttpHandshakeInterceptor(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        // Extract token from query parameters
        String token = request.getURI().getQuery(); // Get the query part of the URI
        String[] params = token.split("&"); // Split by '&' to get individual parameters

        String authToken = null;
        for (String param : params) {
            if (param.startsWith("token=")) {
                authToken = param.substring("token=".length()); // Extract the token value
                break;
            }
        }

        // Validate the token
        if (authToken != null && jwtTokenUtil.validateJwtToken(authToken)) {
            logger.info("Token is valid: {}", authToken);
            return true; // Allow the handshake
        } else {
            logger.error("Invalid token: {}", authToken);
            response.setStatusCode(HttpStatus.UNAUTHORIZED); // Set response status to 401
            return false; // Reject the handshake
        }
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception) {
        // Any additional logic after handshake if needed
    }
}
