package com.ujjwal.job_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "USER_SEQ", allocationSize = 1)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USERNAME", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "PWD_HASH", nullable = false, length = 255)
    private String pwdHash;

    @Column(name = "FULL_NAME", length = 200)
    private String fullName;

    @Column(name = "EMAIL", length = 200)
    private String email;

    @Column(name = "RESET_TOKEN", length = 255)
    private String resetToken;

    @Column(name = "RESET_EXPIRES_AT")
    private LocalDateTime resetExpiresAt;

    // --- Getters and Setters ---
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwdHash() {
        return pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public LocalDateTime getResetExpiresAt() {
        return resetExpiresAt;
    }

    public void setResetExpiresAt(LocalDateTime resetExpiresAt) {
        this.resetExpiresAt = resetExpiresAt;
    }
}
