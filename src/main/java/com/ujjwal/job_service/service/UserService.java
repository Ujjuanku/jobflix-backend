package com.ujjwal.job_service.service;

import com.ujjwal.job_service.entity.UserAccount;
import com.ujjwal.job_service.repository.UserAccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserAccountRepository userAccountRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // initialize encoder
    }

    // 🔹 Get all users
    public List<UserAccount> getAllUsers() {
        return userAccountRepository.findAll();
    }

    // 🔹 Get user by ID
    public Optional<UserAccount> getUserById(Long id) {
        return userAccountRepository.findById(id);
    }

    // 🔹 Get user by username
    public Optional<UserAccount> getUserByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }

    // 🔹 Register user (hash password before saving)
    public UserAccount saveUser(UserAccount user) {
        // hash the raw password before saving
        user.setPwdHash(passwordEncoder.encode(user.getPwdHash()));
        return userAccountRepository.save(user);
    }

    // 🔹 Validate login credentials
    public boolean validateLogin(String username, String rawPassword) {
        Optional<UserAccount> userOpt = userAccountRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            UserAccount user = userOpt.get();
            // compare raw password with stored hash
            return passwordEncoder.matches(rawPassword, user.getPwdHash());
        }
        return false;
    }
}
