package com.ujjwal.job_service.repository;

import com.ujjwal.job_service.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByUser_UserId(Long userId);
    List<Application> findByJobPost_Id(Long jobId);
}
