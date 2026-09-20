package com.klu.pro_application_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

public class ApplicationServiceApplicationTests {

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private WebClient.Builder webClientBuilder;

    @InjectMocks
    private ApplicationService applicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllApplications_shouldReturnApplications() {

        JobApplication application1 = new JobApplication();
        application1.setId(1L);
        application1.setCandidateId(1L);
        application1.setJobId(1L);

        JobApplication application2 = new JobApplication();
        application2.setId(2L);
        application2.setCandidateId(2L);
        application2.setJobId(2L);

        when(applicationRepository.findAll())
                .thenReturn(List.of(application1, application2));

        List<JobApplication> result =
                applicationService.getAllApplications();

        assertEquals(2, result.size());

        verify(applicationRepository, times(1))
                .findAll();
    }

    @Test
    void getApplicationById_shouldReturnApplication() {

        JobApplication application = new JobApplication();

        application.setId(1L);
        application.setCandidateId(1L);
        application.setJobId(1L);

        when(applicationRepository.findById(1L))
                .thenReturn(Optional.of(application));

        JobApplication result =
                applicationService.getApplicationById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getJobId());

        verify(applicationRepository, times(1))
                .findById(1L);
    }

    @Test
    void getApplicationById_shouldThrowExceptionWhenNotFound() {

        when(applicationRepository.findById(999L))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> applicationService.getApplicationById(999L)
                );

        assertEquals(
                "Application not found",
                exception.getMessage()
        );
    }

    @Test
    void deleteApplication_shouldDeleteExistingApplication() {

        when(applicationRepository.existsById(1L))
                .thenReturn(true);

        applicationService.deleteApplication(1L);

        verify(applicationRepository, times(1))
                .deleteById(1L);
    }

    @Test
    void deleteApplication_shouldThrowExceptionWhenNotFound() {

        when(applicationRepository.existsById(999L))
                .thenReturn(false);

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> applicationService.deleteApplication(999L)
                );

        assertEquals(
                "Application not found",
                exception.getMessage()
        );

        verify(applicationRepository, never())
                .deleteById(anyLong());
    }
}