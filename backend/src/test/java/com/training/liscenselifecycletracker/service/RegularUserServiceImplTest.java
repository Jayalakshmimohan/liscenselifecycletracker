package com.training.liscenselifecycletracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.training.liscenselifecycletracker.entities.Device;
import com.training.liscenselifecycletracker.entities.Software;
import com.training.liscenselifecycletracker.exceptions.DeviceNotFoundException;
import com.training.liscenselifecycletracker.exceptions.SoftwareNotFoundException;
import com.training.liscenselifecycletracker.repositories.DeviceRepository;
import com.training.liscenselifecycletracker.repositories.SoftwareRepository;

@ExtendWith(MockitoExtension.class)
public class RegularUserServiceImplTest {

	@Mock
	private DeviceRepository deviceRepository;

	@Mock
	private SoftwareRepository softwareRepository;

	@InjectMocks
	private RegularUserServiceImpl regularUserService;

	private Device device;
	private Software software;

	@BeforeEach
	void setUp() {
		device = new Device(1L, "Device1", "Type1", LocalDate.now(), LocalDate.now().plusYears(1),
				LocalDate.now().plusYears(2), "Active");
		software = new Software(1L, "Software1", "Key123", LocalDate.now(), LocalDate.now().plusDays(20),
				LocalDate.now().plusYears(1), "Active");
	}

	@Test
	void testViewDevices_DevicesExist() throws DeviceNotFoundException {
		when(deviceRepository.findAll()).thenReturn(Arrays.asList(device));

		ResponseEntity<List<Device>> response = regularUserService.viewDevices();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		verify(deviceRepository, times(1)).findAll();
	}

	@Test
	void testViewDevices_NoDevicesFound() {
		when(deviceRepository.findAll()).thenReturn(Collections.emptyList());

		assertThrows(DeviceNotFoundException.class, () -> regularUserService.viewDevices());
		verify(deviceRepository, times(1)).findAll();
	}

	@Test
	void testReceiveNotifications_SoftwareExpiringSoon() {
		when(softwareRepository.findByExpiryDate(any(LocalDate.class))).thenReturn(Arrays.asList(software));

		ResponseEntity<List<String>> response = regularUserService.receiveNotifications();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		assertEquals("Your software license (ID: 1) is expiring soon. Renew now to avoid service disruptions.",
				response.getBody().get(0));
		verify(softwareRepository, times(1)).findByExpiryDate(any(LocalDate.class));
	}

	@Test
	void testReceiveNotifications_NoExpiringSoftware() {
		when(softwareRepository.findByExpiryDate(any(LocalDate.class))).thenReturn(Collections.emptyList());

		ResponseEntity<List<String>> response = regularUserService.receiveNotifications();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		assertEquals("No expiring software licenses found.", response.getBody().get(0));
		verify(softwareRepository, times(1)).findByExpiryDate(any(LocalDate.class));
	}

	@Test
	void testSearchDevicesById_DeviceExists() throws DeviceNotFoundException {
		when(deviceRepository.findById(1L)).thenReturn(java.util.Optional.of(device));

		ResponseEntity<Device> response = regularUserService.searchDevicesById(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(device, response.getBody());
		verify(deviceRepository, times(1)).findById(1L);
	}

	@Test
	void testSearchDevicesById_DeviceNotFound() {
		when(deviceRepository.findById(1L)).thenReturn(java.util.Optional.empty());

		assertThrows(DeviceNotFoundException.class, () -> regularUserService.searchDevicesById(1L));
		verify(deviceRepository, times(1)).findById(1L);
	}

	@Test
	void testSearchDevicesByName_DevicesFound() throws DeviceNotFoundException {
		when(deviceRepository.findByDeviceName("Device1")).thenReturn(Arrays.asList(device));

		ResponseEntity<List<Device>> response = regularUserService.searchDevicesByName("Device1");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		verify(deviceRepository, times(1)).findByDeviceName("Device1");
	}

	@Test
	void testSearchDevicesByName_DeviceNotFound() {
		when(deviceRepository.findByDeviceName("Device1")).thenReturn(Collections.emptyList());

		assertThrows(DeviceNotFoundException.class, () -> regularUserService.searchDevicesByName("Device1"));
		verify(deviceRepository, times(1)).findByDeviceName("Device1");
	}

	@Test
	void testSearchDevicesByType_DevicesFound() throws DeviceNotFoundException {
		when(deviceRepository.findByDeviceType("Type1")).thenReturn(Arrays.asList(device));

		ResponseEntity<List<Device>> response = regularUserService.searchDevicesByType("Type1");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		verify(deviceRepository, times(1)).findByDeviceType("Type1");
	}

	@Test
	void testSearchDevicesByType_DeviceNotFound() {
		when(deviceRepository.findByDeviceType("Type1")).thenReturn(Collections.emptyList());

		assertThrows(DeviceNotFoundException.class, () -> regularUserService.searchDevicesByType("Type1"));
		verify(deviceRepository, times(1)).findByDeviceType("Type1");
	}

	@Test
	void testSearchDevicesByPurchaseDate_DevicesFound() throws DeviceNotFoundException {
		when(deviceRepository.findByPurchaseDate(LocalDate.now())).thenReturn(Arrays.asList(device));

		ResponseEntity<List<Device>> response = regularUserService.searchDevicesByPurchaseDate(LocalDate.now());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		verify(deviceRepository, times(1)).findByPurchaseDate(LocalDate.now());
	}

	@Test
	void testSearchDevicesByPurchaseDate_DeviceNotFound() {
		when(deviceRepository.findByPurchaseDate(LocalDate.now())).thenReturn(Collections.emptyList());

		assertThrows(DeviceNotFoundException.class,
				() -> regularUserService.searchDevicesByPurchaseDate(LocalDate.now()));
		verify(deviceRepository, times(1)).findByPurchaseDate(LocalDate.now());
	}

	@Test
	void testSearchDevicesByExpirationDate_DevicesFound() throws DeviceNotFoundException {
		when(deviceRepository.findByExpirationDate(LocalDate.now().plusYears(1))).thenReturn(Arrays.asList(device));

		ResponseEntity<List<Device>> response = regularUserService
				.searchDevicesByExpirationDate(LocalDate.now().plusYears(1));

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		verify(deviceRepository, times(1)).findByExpirationDate(LocalDate.now().plusYears(1));
	}

	@Test
	void testSearchDevicesByExpirationDate_DeviceNotFound() {
		when(deviceRepository.findByExpirationDate(LocalDate.now().plusYears(1))).thenReturn(Collections.emptyList());

		assertThrows(DeviceNotFoundException.class,
				() -> regularUserService.searchDevicesByExpirationDate(LocalDate.now().plusYears(1)));
		verify(deviceRepository, times(1)).findByExpirationDate(LocalDate.now().plusYears(1));
	}

	@Test
	void testSearchDevicesByStatus_DevicesFound() throws DeviceNotFoundException {
		when(deviceRepository.findByStatus("Active")).thenReturn(Arrays.asList(device));

		ResponseEntity<List<Device>> response = regularUserService.searchDevicesByStatus("Active");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		verify(deviceRepository, times(1)).findByStatus("Active");
	}

	@Test
	void testSearchDevicesByStatus_DeviceNotFound() {
		when(deviceRepository.findByStatus("Active")).thenReturn(Collections.emptyList());

		assertThrows(DeviceNotFoundException.class, () -> regularUserService.searchDevicesByStatus("Active"));
		verify(deviceRepository, times(1)).findByStatus("Active");
	}

	@Test
	void testSearchDevicesBySupportEndDate_DevicesFound() throws DeviceNotFoundException {
		when(deviceRepository.findBySupportEndDate(LocalDate.now().plusYears(2))).thenReturn(Arrays.asList(device));

		ResponseEntity<List<Device>> response = regularUserService
				.searchDevicesBySupportEndDate(LocalDate.now().plusYears(2));

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		verify(deviceRepository, times(1)).findBySupportEndDate(LocalDate.now().plusYears(2));
	}

	@Test
	void testSearchDevicesBySupportEndDate_DeviceNotFound() {
		when(deviceRepository.findBySupportEndDate(LocalDate.now().plusYears(2))).thenReturn(Collections.emptyList());

		assertThrows(DeviceNotFoundException.class,
				() -> regularUserService.searchDevicesBySupportEndDate(LocalDate.now().plusYears(2)));
		verify(deviceRepository, times(1)).findBySupportEndDate(LocalDate.now().plusYears(2));
	}

	@Test
	void testSearchSoftwareById_SoftwareExists() throws SoftwareNotFoundException {
		when(softwareRepository.findBySoftwareId(1L)).thenReturn(Arrays.asList(software));

		ResponseEntity<List<Software>> response = regularUserService.searchSoftwareById(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		verify(softwareRepository, times(1)).findBySoftwareId(1L);
	}

	@Test
	void testSearchSoftwareById_SoftwareNotFound() {
		when(softwareRepository.findBySoftwareId(1L)).thenReturn(Collections.emptyList());

		assertThrows(SoftwareNotFoundException.class, () -> regularUserService.searchSoftwareById(1L));
		verify(softwareRepository, times(1)).findBySoftwareId(1L);
	}

	@Test
	void testSearchSoftwareByName_SoftwareFound() throws SoftwareNotFoundException {
		when(softwareRepository.findBySoftwareName("Software1")).thenReturn(Arrays.asList(software));

		ResponseEntity<List<Software>> response = regularUserService.searchSoftwareByName("Software1");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		verify(softwareRepository, times(1)).findBySoftwareName("Software1");
	}

	@Test
	void testSearchSoftwareByName_SoftwareNotFound() {
		when(softwareRepository.findBySoftwareName("Software1")).thenReturn(Collections.emptyList());

		assertThrows(SoftwareNotFoundException.class, () -> regularUserService.searchSoftwareByName("Software1"));
		verify(softwareRepository, times(1)).findBySoftwareName("Software1");
	}

	@Test
	void testSearchSoftwareByPurchaseDate_SoftwareFound() throws SoftwareNotFoundException {
		when(softwareRepository.findByPurchaseDate(LocalDate.now())).thenReturn(Arrays.asList(software));

		ResponseEntity<List<Software>> response = regularUserService.searchSoftwareByPurchaseDate(LocalDate.now());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		verify(softwareRepository, times(1)).findByPurchaseDate(LocalDate.now());
	}

	@Test
	void testSearchSoftwareByPurchaseDate_SoftwareNotFound() {
		when(softwareRepository.findByPurchaseDate(LocalDate.now())).thenReturn(Collections.emptyList());

		assertThrows(SoftwareNotFoundException.class,
				() -> regularUserService.searchSoftwareByPurchaseDate(LocalDate.now()));
		verify(softwareRepository, times(1)).findByPurchaseDate(LocalDate.now());
	}

	@Test
	void testSearchSoftwareByExpiryDate_SoftwareFound() throws SoftwareNotFoundException {
		when(softwareRepository.findByExpiryDate(LocalDate.now().plusDays(20))).thenReturn(Arrays.asList(software));

		ResponseEntity<List<Software>> response = regularUserService
				.searchSoftwareByExpiryDate(LocalDate.now().plusDays(20));

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		verify(softwareRepository, times(1)).findByExpiryDate(LocalDate.now().plusDays(20));
	}

	@Test
	void testSearchSoftwareByExpiryDate_SoftwareNotFound() {
		when(softwareRepository.findByExpiryDate(LocalDate.now().plusDays(20))).thenReturn(Collections.emptyList());

		assertThrows(SoftwareNotFoundException.class,
				() -> regularUserService.searchSoftwareByExpiryDate(LocalDate.now().plusDays(20)));
		verify(softwareRepository, times(1)).findByExpiryDate(LocalDate.now().plusDays(20));
	}

	@Test
	void testSearchSoftwareBySupportEndDate_SoftwareFound() throws SoftwareNotFoundException {
		when(softwareRepository.findBySupportEndDate(LocalDate.now().plusYears(1))).thenReturn(Arrays.asList(software));

		ResponseEntity<List<Software>> response = regularUserService
				.searchSoftwareBySupportEndDate(LocalDate.now().plusYears(1));

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		verify(softwareRepository, times(1)).findBySupportEndDate(LocalDate.now().plusYears(1));
	}

	@Test
	void testSearchSoftwareBySupportEndDate_SoftwareNotFound() {
		when(softwareRepository.findBySupportEndDate(LocalDate.now().plusYears(1))).thenReturn(Collections.emptyList());

		assertThrows(SoftwareNotFoundException.class,
				() -> regularUserService.searchSoftwareBySupportEndDate(LocalDate.now().plusYears(1)));
		verify(softwareRepository, times(1)).findBySupportEndDate(LocalDate.now().plusYears(1));
	}
}
