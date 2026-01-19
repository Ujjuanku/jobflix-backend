package com.ujjwal.job_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "APPLICATION")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_seq")
    @SequenceGenerator(name = "application_seq", sequenceName = "APPLICATION_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "JOB_ID", referencedColumnName = "ID")
    private JobPost jobPost;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private UserAccount user;

    @Column(name = "APPLICANT_NAME", length = 255)
    private String applicantName;

    @Column(name = "APPLICANT_EMAIL", length = 255)
    private String applicantEmail;

    @Column(name = "RESUME_PATH", length = 512)
    private String resumePath;

    @Lob
    @Column(name = "COVER_LETTER")
    private String coverLetter;

    @Column(name = "SUBMITTED_AT")
    private LocalDateTime submittedAt;

    @Column(name = "STATUS", length = 40)
    private String status;

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public JobPost getJobPost() {
        return jobPost;
    }
    public void setJobPost(JobPost jobPost) {
        this.jobPost = jobPost;
    }

    public UserAccount getUser() {
        return user;
    }
    public void setUser(UserAccount user) {
        this.user = user;
    }

    public String getApplicantName() {
        return applicantName;
    }
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }
    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getResumePath() {
        return resumePath;
    }
    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public String getCoverLetter() {
        return coverLetter;
    }
    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
