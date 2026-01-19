package com.ujjwal.job_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(
        name = "JOB_SKILL",
        uniqueConstraints = @UniqueConstraint(
                name = "UQ_JOB_SKILL_JOB_SKILLNAME",
                columnNames = {"JOB_ID", "SKILL_NAME"}
        )
)
public class JobSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_skill_seq")
    @SequenceGenerator(
            name = "job_skill_seq",
            sequenceName = "JOB_SKILL_SEQ",
            allocationSize = 1
    )
    @Column(name = "SKILL_ID")
    private Long skillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_ID", nullable = false)
    @JsonBackReference
    private JobPost jobPost;

    @Column(name = "SKILL_NAME", nullable = false, length = 200)
    private String skillName;

    /* ---------- Getters & Setters ---------- */

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public JobPost getJobPost() {
        return jobPost;
    }

    public void setJobPost(JobPost jobPost) {
        this.jobPost = jobPost;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
