package com.ujjwal.job_service.service;

import com.ujjwal.job_service.entity.Application;          // ✅ add this import
import com.ujjwal.job_service.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<Application> getApplicationsByUser(Long userId) {
        return applicationRepository.findByUser_UserId(userId);
    }

    public List<Application> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobPost_Id(jobId);
    }

    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }
}
