package com.training.liscenselifecycletracker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.training.liscenselifecycletracker.service.ManagementService;

@ExtendWith(MockitoExtension.class)
class ManagementControllerTest {

    @Mock
    ManagementService managementService;

    @InjectMocks
    ManagementController managementController;

    @Test
    void testOverseeLifecycle() {
        // Given
        Long assetId = 1L;

        // When
        ResponseEntity<String> responseEntity = managementController.overseeLifecycle(assetId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Overseeing lifecycle for asset with ID: 1", responseEntity.getBody());
        verify(managementService).overseeLifecycle(assetId);
    }

    @Test
    void testGenerateLifecycleReports() {
        // Given
        Long assetId = 1L;

        // When
        ResponseEntity<String> responseEntity = managementController.generateLifecycleReports(assetId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Generating reports for asset with ID: 1", responseEntity.getBody());
        verify(managementService).generateLifecycleReports(assetId);
    }
}
