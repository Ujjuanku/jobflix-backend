package com.ujjwal.job_service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "JOB_POST")
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_post_seq")
    @SequenceGenerator(
            name = "job_post_seq",
            sequenceName = "JOB_POST_SEQ",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", length = 1020, nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", length = 4000)
    private String description;

    @Column(name = "LOCATION", length = 1020, nullable = false)
    private String location;

    @Column(name = "SALARY", precision = 19, scale = 2, nullable = false)
    private BigDecimal salary;

    @Column(name = "SALARY_TEXT", length = 200)
    private String salaryText;

    @Column(name = "COMPANY", length = 200, nullable = false)
    private String company;

    @Column(name = "WORK_MODE", length = 50)
    private String workMode;

    @Column(name = "EDUCATION", length = 100)
    private String education;

    @Column(name = "EXPERIENCE_LEVEL", length = 100)
    private String experienceLevel;

    @Column(name = "JOB_TYPE", length = 50)
    private String jobType;

    @Column(name = "POSTED_AT", nullable = false)
    private LocalDateTime postedAt;

    @Column(name = "COMPANY_LOGO", length = 500)
    private String companyLogo;

    @OneToMany(mappedBy = "jobPost", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<JobSkill> skills;

    /* ---------- Lifecycle ---------- */

    @PrePersist
    protected void onCreate() {
        this.postedAt = LocalDateTime.now();
    }

    /* ---------- Getters & Setters ---------- */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getSalaryText() {
        return salaryText;
    }

    public void setSalaryText(String salaryText) {
        this.salaryText = salaryText;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWorkMode() {
        return workMode;
    }

    public void setWorkMode(String workMode) {
        this.workMode = workMode;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public List<JobSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<JobSkill> skills) {
        this.skills = skills;
    }
}
