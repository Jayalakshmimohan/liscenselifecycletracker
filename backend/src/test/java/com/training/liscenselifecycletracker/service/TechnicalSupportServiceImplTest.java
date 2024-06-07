package com.training.liscenselifecycletracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.training.liscenselifecycletracker.entities.Device;
import com.training.liscenselifecycletracker.entities.LifecycleEvent;
import com.training.liscenselifecycletracker.entities.Software;
import com.training.liscenselifecycletracker.repositories.DeviceRepository;
import com.training.liscenselifecycletracker.repositories.LifecycleEventRepository;
import com.training.liscenselifecycletracker.repositories.SoftwareRepository;

@ExtendWith(MockitoExtension.class)
public class TechnicalSupportServiceImplTest {

	@Mock
	private DeviceRepository deviceRepository;

	@Mock
	private SoftwareRepository softwareRepository;

	@Mock
	private LifecycleEventRepository lifecycleEventRepository;

	@InjectMocks
	private TechnicalSupportServiceImpl technicalSupportService;

	private Device device;
	private Software software;
	private LifecycleEvent lifecycleEvent;

	@BeforeEach
	public void setUp() {
		device = new Device(1L, "Device1", "Type1", LocalDate.of(2020, 1, 1), LocalDate.of(2025, 1, 1),
				LocalDate.of(2024, 1, 1), "Active");
		software = new Software(1L, "Software1", "Key1", LocalDate.of(2020, 1, 1), LocalDate.of(2023, 1, 1),
				LocalDate.of(2022, 1, 1), "Active");
		lifecycleEvent = new LifecycleEvent(1L, 1L, "Audit", LocalDate.of(2022, 1, 1), "Initial Description",
				"Category1");
	}

	@Test
	public void testLogFault_DeviceNotFound() {
		when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			technicalSupportService.logFault(1L, "Fault description", "2023-06-01", "Category1");
		});

		assertEquals("Device not found with ID: 1", exception.getMessage());
	}

	@Test
	public void testLogFault_NewEvent() {
		when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
		when(lifecycleEventRepository.findByAssetId(1L)).thenReturn(null);

		technicalSupportService.logFault(1L, "Fault description", "2023-06-01", "Category1");

		verify(lifecycleEventRepository, times(1)).save(any(LifecycleEvent.class));
	}

	@Test
	public void testLogFault_UpdateEvent() {
		when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
		when(lifecycleEventRepository.findByAssetId(1L)).thenReturn(lifecycleEvent);

		technicalSupportService.logFault(1L, "Updated description", "2023-06-01", "Category1");

		assertEquals("Updated description", lifecycleEvent.getDescription());
		assertEquals(LocalDate.of(2023, 6, 1), lifecycleEvent.getEventDate());
		verify(lifecycleEventRepository, times(1)).save(lifecycleEvent);
	}

	@Test
	public void testUpdateFaultLog_NoExistingFaultLog() {
		when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
		when(lifecycleEventRepository.findByAssetIdAndEventType(1L, "Audit")).thenReturn(Optional.empty());

		Exception exception = assertThrows(IllegalStateException.class, () -> {
			technicalSupportService.updateFaultLog(1L, "Repair details", "Category1", "Audit");
		});

		assertEquals("No existing fault log found for device ID: 1", exception.getMessage());
	}

	@Test
	public void testUpdateFaultLog_Success() {
		when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
		when(lifecycleEventRepository.findByAssetIdAndEventType(1L, "Audit")).thenReturn(Optional.of(lifecycleEvent));

		technicalSupportService.updateFaultLog(1L, "Repair details", "Category1", "Audit");

		assertEquals("Repair details", lifecycleEvent.getDescription());
		assertEquals("Resolved", lifecycleEvent.getEventType());
		verify(lifecycleEventRepository, times(1)).save(lifecycleEvent);
	}

	@Test
	public void testViewEndOfSupportDates() {
		List<Device> devices = new ArrayList<>();
		devices.add(device);

		List<Software> softwareList = new ArrayList<>();
		softwareList.add(software);

		when(deviceRepository.findAll()).thenReturn(devices);
		when(softwareRepository.findAll()).thenReturn(softwareList);

		List<String> supportDates = technicalSupportService.viewEndOfSupportDates();

		assertEquals(2, supportDates.size());
		assertTrue(supportDates.contains("Asset ID: 1, Asset Name: Device1, End of Support Date: 2024-01-01"));
		assertTrue(supportDates.contains("Asset ID: 1, Asset Name: Software1, End of Support Date: 2022-01-01"));
	}
}
