package com.klu.pro_application_service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository
        extends JpaRepository<JobApplication, Long> {
}