package com.ujjwal.job_service.controller;

import com.ujjwal.job_service.entity.JobPost;
import com.ujjwal.job_service.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    /* 📄 List jobs with pagination */
    @GetMapping
    public ResponseEntity<Page<JobPost>> listJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(jobService.getAllJobs(page, size));
    }

    /* 🔍 Get job by ID */
    @GetMapping("/{id}")
    public ResponseEntity<JobPost> getJob(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    /* 🔎 Search jobs */
    @GetMapping("/search")
    public ResponseEntity<Page<JobPost>> searchJobs(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                jobService.searchJobs(keyword, page, size)
        );
    }

    /* 🎯 Filter jobs */
    @GetMapping("/filter")
    public ResponseEntity<Page<JobPost>> filterJobs(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String workMode,
            @RequestParam(required = false) String experienceLevel,
            @RequestParam(required = false) BigDecimal minSalary,
            @RequestParam(required = false) BigDecimal maxSalary,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                jobService.filterJobs(
                        location,
                        workMode,
                        experienceLevel,
                        minSalary,
                        maxSalary,
                        page,
                        size
                )
        );
    }

    /* ➕ Create job */
    @PostMapping
    public ResponseEntity<JobPost> createJob(@RequestBody JobPost jobPost) {
        JobPost saved = jobService.saveJob(jobPost);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /* ✏ Update job */
    @PutMapping("/{id}")
    public ResponseEntity<JobPost> updateJob(
            @PathVariable Long id,
            @RequestBody JobPost updatedJob
    ) {
        JobPost existing = jobService.getJobById(id);

        existing.setTitle(updatedJob.getTitle());
        existing.setDescription(updatedJob.getDescription());
        existing.setLocation(updatedJob.getLocation());
        existing.setSalary(updatedJob.getSalary());
        existing.setSalaryText(updatedJob.getSalaryText());
        existing.setCompany(updatedJob.getCompany());
        existing.setWorkMode(updatedJob.getWorkMode());
        existing.setEducation(updatedJob.getEducation());
        existing.setExperienceLevel(updatedJob.getExperienceLevel());
        existing.setJobType(updatedJob.getJobType());
        existing.setCompanyLogo(updatedJob.getCompanyLogo());

        return ResponseEntity.ok(jobService.saveJob(existing));
    }

    /* 🗑 Delete job */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}
