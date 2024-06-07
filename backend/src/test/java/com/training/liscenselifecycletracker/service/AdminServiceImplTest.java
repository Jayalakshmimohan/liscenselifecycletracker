package com.training.liscenselifecycletracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.training.liscenselifecycletracker.entities.Device;
import com.training.liscenselifecycletracker.entities.LifecycleEvent;
import com.training.liscenselifecycletracker.entities.Software;
import com.training.liscenselifecycletracker.entities.User;
import com.training.liscenselifecycletracker.exceptions.DeviceNotFoundException;
import com.training.liscenselifecycletracker.exceptions.LifecycleEventNotFoundException;
import com.training.liscenselifecycletracker.exceptions.SoftwareNotFoundException;
import com.training.liscenselifecycletracker.exceptions.UserNotFoundException;
import com.training.liscenselifecycletracker.repositories.DeviceRepository;
import com.training.liscenselifecycletracker.repositories.LifecycleEventRepository;
import com.training.liscenselifecycletracker.repositories.SoftwareRepository;
import com.training.liscenselifecycletracker.repositories.UserRepository;

class AdminServiceImplTest {

	@Mock
	private DeviceRepository deviceRepository;

	@Mock
	private SoftwareRepository softwareRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private LifecycleEventRepository lifecycleEventRepository;

	@InjectMocks
	private AdminServiceImpl adminService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddDevice() {
		Device device = new Device();
		adminService.addDevice(device);
		verify(deviceRepository, times(1)).save(device);
	}

	@Test
	void testUpdateDevice() throws DeviceNotFoundException {
		Device device = new Device();
		device.setDeviceId(1L);
		when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
		adminService.updateDevice(device);
		verify(deviceRepository, times(1)).save(device);
	}

	@Test
	void testUpdateDeviceNotFound() {
		Device device = new Device();
		device.setDeviceId(1L);
		when(deviceRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(DeviceNotFoundException.class, () -> adminService.updateDevice(device));
		verify(deviceRepository, never()).save(any(Device.class));
	}

	@Test
	void testDeleteDevice() throws DeviceNotFoundException {
		Long deviceId = 1L;
		when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(new Device()));
		adminService.deleteDevice(deviceId);
		verify(deviceRepository, times(1)).deleteById(deviceId);
	}

	@Test
	void testDeleteDeviceNotFound() {
		Long deviceId = 1L;
		when(deviceRepository.findById(deviceId)).thenReturn(Optional.empty());
		assertThrows(DeviceNotFoundException.class, () -> adminService.deleteDevice(deviceId));
		verify(deviceRepository, never()).deleteById(any());
	}

	@Test
	void testAddSoftware() {
		Software software = new Software();
		adminService.addSoftware(software);
		verify(softwareRepository, times(1)).save(software);
	}

	@Test
	void testUpdateSoftware() throws SoftwareNotFoundException {
		Software software = new Software();
		software.setSoftwareId(1L);
		when(softwareRepository.findById(1L)).thenReturn(Optional.of(software));
		adminService.updateSoftware(software);
		verify(softwareRepository, times(1)).save(software);
	}

	@Test
	void testUpdateSoftwareNotFound() {
		Software software = new Software();
		software.setSoftwareId(1L);
		when(softwareRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(SoftwareNotFoundException.class, () -> adminService.updateSoftware(software));
		verify(softwareRepository, never()).save(any(Software.class));
	}

	@Test
	void testDeleteSoftware() throws SoftwareNotFoundException {
		Long softwareId = 1L;
		when(softwareRepository.findById(softwareId)).thenReturn(Optional.of(new Software()));
		adminService.deleteSoftware(softwareId);
		verify(softwareRepository, times(1)).deleteById(softwareId);
	}

	@Test
	void testDeleteSoftwareNotFound() {
		Long softwareId = 1L;
		when(softwareRepository.findById(softwareId)).thenReturn(Optional.empty());
		assertThrows(SoftwareNotFoundException.class, () -> adminService.deleteSoftware(softwareId));
		verify(softwareRepository, never()).deleteById(any());
	}

	@Test
	void testAddUser() {
		User user = new User();
		adminService.addUser(user);
		verify(userRepository, times(1)).save(user);
	}

	@Test
	void testUpdateUser() throws UserNotFoundException {
		User user = new User();
		user.setUserId(1L);
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		adminService.updateUser(user);
		verify(userRepository, times(1)).save(user);
	}

	@Test
	void testUpdateUserNotFound() {
		User user = new User();
		user.setUserId(1L);
		when(userRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> adminService.updateUser(user));
		verify(userRepository, never()).save(any(User.class));
	}

	@Test
	void testDeleteUser() throws UserNotFoundException {
		Long userId = 1L;
		when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
		adminService.deleteUser(userId);
		verify(userRepository, times(1)).deleteById(userId);
	}

	@Test
	void testDeleteUserNotFound() {
		Long userId = 1L;
		when(userRepository.findById(userId)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> adminService.deleteUser(userId));
		verify(userRepository, never()).deleteById(any());
	}

	@Test
	void testAddLifecycleEvent() {
		LifecycleEvent event = new LifecycleEvent();
		adminService.addLifecycleEvent(event);
		verify(lifecycleEventRepository, times(1)).save(event);
	}

	@Test
	void testUpdateLifecycleEvent() throws LifecycleEventNotFoundException {
		LifecycleEvent event = new LifecycleEvent();
		event.setEventId(1L);
		when(lifecycleEventRepository.findById(1L)).thenReturn(Optional.of(event));
		adminService.updateLifecycleEvent(event);
		verify(lifecycleEventRepository, times(1)).save(event);
	}

	@Test
	void testUpdateLifecycleEventNotFound() {
		LifecycleEvent event = new LifecycleEvent();
		event.setEventId(1L);
		when(lifecycleEventRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(LifecycleEventNotFoundException.class, () -> adminService.updateLifecycleEvent(event));
		verify(lifecycleEventRepository, never()).save(any(LifecycleEvent.class));
	}

	@Test
	void testDeleteLifecycleEvent() throws LifecycleEventNotFoundException {
		Long eventId = 1L;
		LifecycleEvent event = new LifecycleEvent();
		event.setEventId(eventId);
		when(lifecycleEventRepository.findById(eventId)).thenReturn(Optional.of(event));
		doNothing().when(lifecycleEventRepository).delete(event);
		adminService.deleteLifecycleEvent(eventId);
		verify(lifecycleEventRepository, times(1)).delete(event);
	}

	@Test
	void testDeleteLifecycleEventNotFound() {
		Long eventId = 1L;
		when(lifecycleEventRepository.findById(eventId)).thenReturn(Optional.empty());
		assertThrows(LifecycleEventNotFoundException.class, () -> adminService.deleteLifecycleEvent(eventId));
		verify(lifecycleEventRepository, never()).delete(any(LifecycleEvent.class));
	}

	@Test
	void testViewDevices() throws DeviceNotFoundException {
		List<Device> devices = new ArrayList<>();
		devices.add(new Device());
		when(deviceRepository.findAll()).thenReturn(devices);
		ResponseEntity<List<Device>> response = adminService.viewDevices();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
	}

	@Test
	void testViewDevicesNotFound() {
		when(deviceRepository.findAll()).thenReturn(new ArrayList<>());
		assertThrows(DeviceNotFoundException.class, () -> adminService.viewDevices());
	}

	@Test
	void testSearchDevicesById() throws DeviceNotFoundException {
		Long deviceId = 1L;
		Device device = new Device();
		device.setDeviceId(deviceId);
		when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));
		ResponseEntity<Device> response = adminService.searchDevicesById(deviceId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(deviceId, response.getBody().getDeviceId());
	}

	@Test
	void testSearchDevicesByIdNotFound() {
		Long deviceId = 1L;
		when(deviceRepository.findById(deviceId)).thenReturn(Optional.empty());
		assertThrows(DeviceNotFoundException.class, () -> adminService.searchDevicesById(deviceId));
	}
}