package com.eldar.ISlab1.security.controller;

import com.eldar.ISlab1.redis.JwtBlacklistService;
import com.eldar.ISlab1.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LogoutController {
    private final JwtBlacklistService jwtBlacklistService;

    @PostMapping("/logout")
    public ResponseEntity<String> logoutClient(HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    jwtBlacklistService.blacklistToken(token);
                    break;
                }
            }
        }

        Cookie cookie = new Cookie("token", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Enable in production (HTTPS only)
        cookie.setPath("/");
        cookie.setMaxAge(0); // Expire immediately
        response.addCookie(cookie);

        return ResponseEntity.ok("Logout successful");
    }

}
