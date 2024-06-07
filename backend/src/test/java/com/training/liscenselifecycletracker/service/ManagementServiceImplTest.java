package com.training.liscenselifecycletracker.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.training.liscenselifecycletracker.entities.LifecycleEvent;
import com.training.liscenselifecycletracker.repositories.LifecycleEventRepository;

@ExtendWith(MockitoExtension.class)
public class ManagementServiceImplTest {

	@Mock
	private LifecycleEventRepository lifecycleEventRepository;

	@InjectMocks
	private ManagementServiceImpl managementService;

	private LifecycleEvent lifecycleEvent;

	@BeforeEach
	void setUp() {
		lifecycleEvent = new LifecycleEvent();
		lifecycleEvent.setEventId(1L);
		lifecycleEvent.setAssetId(100L);
		lifecycleEvent.setEventType("CREATED");
		lifecycleEvent.setEventDate(LocalDate.now());
		lifecycleEvent.setDescription("Device created");
		lifecycleEvent.setCategory("INFORMATION");
	}

	@Test
	void testOverseeLifecycle_EventExists() {
		when(lifecycleEventRepository.findByAssetId(100L)).thenReturn(lifecycleEvent);

		managementService.overseeLifecycle(100L);

		verify(lifecycleEventRepository, times(1)).findByAssetId(100L);
		// Additional verification or assertions can be added here
	}

	@Test
	void testOverseeLifecycle_EventDoesNotExist() {
		when(lifecycleEventRepository.findByAssetId(100L)).thenReturn(null);

		managementService.overseeLifecycle(100L);

		verify(lifecycleEventRepository, times(1)).findByAssetId(100L);
		// Additional verification or assertions can be added here
	}

	@Test
	void testGenerateLifecycleReports_EventExists() {
		when(lifecycleEventRepository.findByAssetId(100L)).thenReturn(lifecycleEvent);

		managementService.generateLifecycleReports(100L);

		verify(lifecycleEventRepository, times(1)).findByAssetId(100L);
		// Additional verification or assertions can be added here
	}

	@Test
	void testGenerateLifecycleReports_EventDoesNotExist() {
		when(lifecycleEventRepository.findByAssetId(100L)).thenReturn(null);

		managementService.generateLifecycleReports(100L);

		verify(lifecycleEventRepository, times(1)).findByAssetId(100L);
		// Additional verification or assertions can be added here
	}
}
