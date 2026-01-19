package com.ujjwal.job_service.repository;

import com.ujjwal.job_service.entity.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, Long> {
    List<JobSkill> findByJobPost_Id(Long jobId);
}
