package com.ujjwal.job_service.controller;

import com.ujjwal.job_service.dto.LoginRequest;
import com.ujjwal.job_service.entity.UserAccount;
import com.ujjwal.job_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin // CORS already handled globally
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /* ================= REGISTER ================= */

    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(@RequestBody UserAccount user) {
        UserAccount saved = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /* ================= LOGIN ================= */

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        Optional<UserAccount> userOpt =
                userService.getUserByUsername(loginRequest.getUsername());

        // ✅ USER NOT FOUND → 401 (NOT 500)
        if (userOpt.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }

        UserAccount user = userOpt.get();

        // ✅ SAFE PASSWORD CHECK (handled in service)
        boolean valid =
                userService.validateLogin(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                );

        if (!valid) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }

        // ✅ SUCCESS
        return ResponseEntity.ok(new LoginResponse("mock-token-123", user));
    }

    /* ================= GET USER ================= */

    @GetMapping("/user/{id}")
    public ResponseEntity<UserAccount> getUser(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /* ================= RESPONSE DTO ================= */

    public static class LoginResponse {
        private final String token;
        private final UserAccount user;

        public LoginResponse(String token, UserAccount user) {
            this.token = token;
            this.user = user;
        }

        public String getToken() { return token; }
        public UserAccount getUser() { return user; }
    }
}
