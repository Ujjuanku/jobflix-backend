package com.ujjwal.job_service.repository;

import com.ujjwal.job_service.entity.JobPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {

    /* 🔍 Search by title or company */
    @Query("""
        SELECT j FROM JobPost j
        WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(j.company) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    Page<JobPost> searchByKeyword(
            @Param("keyword") String keyword,
            Pageable pageable
    );

    /* 📍 Filter by location */
    Page<JobPost> findByLocationIgnoreCase(
            String location,
            Pageable pageable
    );

    /* 💼 Filter by work mode */
    Page<JobPost> findByWorkModeIgnoreCase(
            String workMode,
            Pageable pageable
    );

    /* 🧠 Filter by experience level */
    Page<JobPost> findByExperienceLevelIgnoreCase(
            String experienceLevel,
            Pageable pageable
    );

    /* 💰 Salary range filter */
    Page<JobPost> findBySalaryBetween(
            BigDecimal minSalary,
            BigDecimal maxSalary,
            Pageable pageable
    );
}
