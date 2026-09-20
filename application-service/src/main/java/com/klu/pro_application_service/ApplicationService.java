package com.klu.pro_application_service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final WebClient.Builder webClientBuilder;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            WebClient.Builder webClientBuilder) {

        this.applicationRepository = applicationRepository;
        this.webClientBuilder = webClientBuilder;
    }

    public JobApplication createApplication(JobApplication application) {

        // Check whether the job exists in Job Service
        JobResponse job = webClientBuilder
                .build()
                .get()
                .uri(
                    "http://JOB-SERVICE/jobs/{id}",
                    application.getJobId()
                )
                .retrieve()
                .bodyToMono(JobResponse.class)
                .block();

        // If job does not exist, reject the application
        if (job == null) {
            throw new RuntimeException(
                    "Job not found. Application cannot be created."
            );
        }

        // Set default status
        if (application.getStatus() == null ||
            application.getStatus().isBlank()) {

            application.setStatus("APPLIED");
        }

        // Save only after job is verified
        return applicationRepository.save(application);
    }

    public List<JobApplication> getAllApplications() {

        return applicationRepository.findAll();
    }

    public JobApplication getApplicationById(Long id) {

        return applicationRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException("Application not found")
                );
    }

    public JobApplication updateApplication(
            Long id,
            JobApplication updatedApplication) {

        JobApplication existingApplication =
                applicationRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException("Application not found")
                );

        existingApplication.setCandidateId(
                updatedApplication.getCandidateId());

        existingApplication.setJobId(
                updatedApplication.getJobId());

        existingApplication.setResume(
                updatedApplication.getResume());

        existingApplication.setCoverLetter(
                updatedApplication.getCoverLetter());

        existingApplication.setStatus(
                updatedApplication.getStatus());

        return applicationRepository.save(existingApplication);
    }

    public void deleteApplication(Long id) {

        if (!applicationRepository.existsById(id)) {
            throw new RuntimeException("Application not found");
        }

        applicationRepository.deleteById(id);
    }
}