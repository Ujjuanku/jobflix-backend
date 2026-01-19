package com.ujjwal.job_service.service;

import com.ujjwal.job_service.entity.JobPost;
import com.ujjwal.job_service.repository.JobPostRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class JobService {

    private final JobPostRepository repository;
    private final EntityManager em;

    public JobService(JobPostRepository repository, EntityManager em) {
        this.repository = repository;
        this.em = em;
    }

    /* 📄 Oracle-safe paginated job list */
    public Page<JobPost> getAllJobs(int page, int size) {

        int offset = page * size;

        List<JobPost> jobs = em.createQuery(
                        "SELECT j FROM JobPost j ORDER BY j.postedAt DESC",
                        JobPost.class
                )
                .setFirstResult(offset)
                .setMaxResults(size)
                .getResultList();

        Long total = em.createQuery(
                "SELECT COUNT(j) FROM JobPost j",
                Long.class
        ).getSingleResult();

        return new PageImpl<>(jobs, PageRequest.of(page, size), total);
    }

    /* 🔍 Get job by ID */
    public JobPost getJobById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
    }

    /* 💾 Create / Update job */
    public JobPost saveJob(JobPost jobPost) {
        return repository.save(jobPost);
    }

    /* 🗑 Delete job */
    public void deleteJob(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Job not found with id: " + id);
        }
        repository.deleteById(id);
    }

    /* 🔎 Oracle-safe keyword search */
    public Page<JobPost> searchJobs(String keyword, int page, int size) {

        int offset = page * size;

        List<JobPost> jobs = em.createQuery(
                        """
                        SELECT j FROM JobPost j
                        WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :k, '%'))
                           OR LOWER(j.company) LIKE LOWER(CONCAT('%', :k, '%'))
                        ORDER BY j.postedAt DESC
                        """,
                        JobPost.class
                )
                .setParameter("k", keyword)
                .setFirstResult(offset)
                .setMaxResults(size)
                .getResultList();

        Long total = em.createQuery(
                        """
                        SELECT COUNT(j) FROM JobPost j
                        WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :k, '%'))
                           OR LOWER(j.company) LIKE LOWER(CONCAT('%', :k, '%'))
                        """,
                        Long.class
                )
                .setParameter("k", keyword)
                .getSingleResult();

        return new PageImpl<>(jobs, PageRequest.of(page, size), total);
    }

    /* 🎯 Oracle-safe filters */
    public Page<JobPost> filterJobs(
            String location,
            String workMode,
            String experienceLevel,
            BigDecimal minSalary,
            BigDecimal maxSalary,
            int page,
            int size
    ) {

        int offset = page * size;

        StringBuilder jpql = new StringBuilder("SELECT j FROM JobPost j WHERE 1=1");
        StringBuilder countJpql = new StringBuilder("SELECT COUNT(j) FROM JobPost j WHERE 1=1");

        if (location != null) {
            jpql.append(" AND LOWER(j.location) = LOWER(:location)");
            countJpql.append(" AND LOWER(j.location) = LOWER(:location)");
        }
        if (workMode != null) {
            jpql.append(" AND LOWER(j.workMode) = LOWER(:workMode)");
            countJpql.append(" AND LOWER(j.workMode) = LOWER(:workMode)");
        }
        if (experienceLevel != null) {
            jpql.append(" AND LOWER(j.experienceLevel) = LOWER(:experienceLevel)");
            countJpql.append(" AND LOWER(j.experienceLevel) = LOWER(:experienceLevel)");
        }
        if (minSalary != null && maxSalary != null) {
            jpql.append(" AND j.salary BETWEEN :minSalary AND :maxSalary");
            countJpql.append(" AND j.salary BETWEEN :minSalary AND :maxSalary");
        }

        jpql.append(" ORDER BY j.postedAt DESC");

        var query = em.createQuery(jpql.toString(), JobPost.class);
        var countQuery = em.createQuery(countJpql.toString(), Long.class);

        if (location != null) {
            query.setParameter("location", location);
            countQuery.setParameter("location", location);
        }
        if (workMode != null) {
            query.setParameter("workMode", workMode);
            countQuery.setParameter("workMode", workMode);
        }
        if (experienceLevel != null) {
            query.setParameter("experienceLevel", experienceLevel);
            countQuery.setParameter("experienceLevel", experienceLevel);
        }
        if (minSalary != null && maxSalary != null) {
            query.setParameter("minSalary", minSalary);
            query.setParameter("maxSalary", maxSalary);
            countQuery.setParameter("minSalary", minSalary);
            countQuery.setParameter("maxSalary", maxSalary);
        }

        List<JobPost> jobs = query
                .setFirstResult(offset)
                .setMaxResults(size)
                .getResultList();

        Long total = countQuery.getSingleResult();

        return new PageImpl<>(jobs, PageRequest.of(page, size), total);
    }
}
