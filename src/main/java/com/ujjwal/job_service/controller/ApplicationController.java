package com.ujjwal.job_service.controller;

import com.ujjwal.job_service.entity.Application;
import com.ujjwal.job_service.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/user/{userId}")
    public List<Application> getApplicationsByUser(@PathVariable Long userId) {
        return applicationService.getApplicationsByUser(userId);
    }

    @GetMapping("/job/{jobId}")
    public List<Application> getApplicationsByJob(@PathVariable Long jobId) {
        return applicationService.getApplicationsByJob(jobId);
    }

    @PostMapping
    public Application createApplication(@RequestBody Application application) {
        return applicationService.saveApplication(application);
    }
}
