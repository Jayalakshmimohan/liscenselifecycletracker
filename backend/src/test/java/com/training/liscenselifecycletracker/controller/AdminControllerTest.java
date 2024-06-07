package com.training.liscenselifecycletracker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.training.liscenselifecycletracker.entities.Device;
import com.training.liscenselifecycletracker.entities.LifecycleEvent;
import com.training.liscenselifecycletracker.entities.Role;
import com.training.liscenselifecycletracker.entities.Software;
import com.training.liscenselifecycletracker.entities.User;
import com.training.liscenselifecycletracker.exceptions.DeviceNotFoundException;
import com.training.liscenselifecycletracker.exceptions.LifecycleEventNotFoundException;
import com.training.liscenselifecycletracker.exceptions.SoftwareNotFoundException;
import com.training.liscenselifecycletracker.exceptions.UserNotFoundException;
import com.training.liscenselifecycletracker.service.AdminService;
import com.training.liscenselifecycletracker.service.UserService;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

	@Mock
	private AdminService adminService;

	@Mock
	private UserService userService;

	@InjectMocks
	private AdminController adminController;

	@Test
	void testAddDevice_Success() {
		Device device = new Device();
		ResponseEntity<String> response = adminController.addDevice(device);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Device added successfully", response.getBody());
		verify(adminService).addDevice(device);
	}

	@Test
	void testUpdateDevice_Success() throws DeviceNotFoundException {
		Device updatedDevice = new Device();
		ResponseEntity<String> response = adminController.updateDevice(updatedDevice);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Device updated successfully", response.getBody());
		verify(adminService).updateDevice(updatedDevice);
	}

	@Test
	void testUpdateDevice_DeviceNotFoundException() throws DeviceNotFoundException {
		Device updatedDevice = new Device();
		doThrow(new DeviceNotFoundException("Device not found")).when(adminService).updateDevice(updatedDevice);
		ResponseEntity<String> response = adminController.updateDevice(updatedDevice);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Device not found", response.getBody());
	}

	@Test
	void testDeleteDevice_Success() throws DeviceNotFoundException {
		Long deviceId = 1L;
		ResponseEntity<String> response = adminController.deleteDevice(deviceId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Device deleted successfully", response.getBody());
		verify(adminService).deleteDevice(deviceId);
	}

	@Test
	void testDeleteDevice_DeviceNotFoundException() throws DeviceNotFoundException {
		Long deviceId = 1L;
		doThrow(new DeviceNotFoundException("Device not found")).when(adminService).deleteDevice(deviceId);
		ResponseEntity<String> response = adminController.deleteDevice(deviceId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Device not found", response.getBody());
	}

	@Test
	void testAddSoftware_Success() {
		Software software = new Software();
		ResponseEntity<String> response = adminController.addSoftware(software);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Software added successfully", response.getBody());
		verify(adminService).addSoftware(software);
	}

	@Test
	void testUpdateSoftware_Success() throws SoftwareNotFoundException {
		Software updatedSoftware = new Software();
		ResponseEntity<String> response = adminController.updateSoftware(updatedSoftware);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Software updated successfully", response.getBody());
		verify(adminService).updateSoftware(updatedSoftware);
	}

	@Test
	void testUpdateSoftware_SoftwareNotFoundException() throws SoftwareNotFoundException {
		Software updatedSoftware = new Software();
		doThrow(new SoftwareNotFoundException("Software not found")).when(adminService).updateSoftware(updatedSoftware);
		ResponseEntity<String> response = adminController.updateSoftware(updatedSoftware);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Software not found", response.getBody());
	}

	@Test
	void testDeleteSoftware_Success() throws SoftwareNotFoundException {
		Long softwareId = 1L;
		ResponseEntity<String> response = adminController.deleteSoftware(softwareId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Software deleted successfully", response.getBody());
		verify(adminService).deleteSoftware(softwareId);
	}

	@Test
	void testDeleteSoftware_SoftwareNotFoundException() throws SoftwareNotFoundException {
		Long softwareId = 1L;
		doThrow(new SoftwareNotFoundException("Software not found")).when(adminService).deleteSoftware(softwareId);
		ResponseEntity<String> response = adminController.deleteSoftware(softwareId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Software not found", response.getBody());
	}

	@Test
	void testAddUser_Success() {
		User user = new User();
		ResponseEntity<String> response = adminController.addUser(user);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("User added successfully", response.getBody());
		verify(adminService).addUser(user);
	}

	@Test
	void testUpdateUser_Success() throws UserNotFoundException {
		User updatedUser = new User();
		ResponseEntity<String> response = adminController.updateUser(updatedUser);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("User updated successfully", response.getBody());
		verify(adminService).updateUser(updatedUser);
	}

	@Test
	void testUpdateUser_UserNotFoundException() throws UserNotFoundException {
		User updatedUser = new User();
		doThrow(new UserNotFoundException("User not found")).when(adminService).updateUser(updatedUser);
		ResponseEntity<String> response = adminController.updateUser(updatedUser);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("User not found", response.getBody());
	}

	@Test
	void testDeleteUser_Success() throws UserNotFoundException {
		Long userId = 1L;
		ResponseEntity<String> response = adminController.deleteUser(userId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("User deleted successfully", response.getBody());
		verify(adminService).deleteUser(userId);
	}

	@Test
	void testDeleteUser_UserNotFoundException() throws UserNotFoundException {
		Long userId = 1L;
		doThrow(new UserNotFoundException("User not found")).when(adminService).deleteUser(userId);
		ResponseEntity<String> response = adminController.deleteUser(userId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("User not found", response.getBody());
	}

	@Test
	void testAddLifecycleEvent_Success() {
		LifecycleEvent lifecycleEvent = new LifecycleEvent();
		ResponseEntity<String> response = adminController.addLifecycleEvent(lifecycleEvent);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Lifecycle event added successfully", response.getBody());
		verify(adminService).addLifecycleEvent(lifecycleEvent);
	}

	@Test
	void testUpdateLifecycleEvent_Success() throws LifecycleEventNotFoundException {
		LifecycleEvent updatedLifecycleEvent = new LifecycleEvent();
		ResponseEntity<String> response = adminController.updateLifecycleEvent(updatedLifecycleEvent);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Lifecycle event updated successfully", response.getBody());
		verify(adminService).updateLifecycleEvent(updatedLifecycleEvent);
	}

	@Test
	void testUpdateLifecycleEvent_LifecycleEventNotFoundException() throws LifecycleEventNotFoundException {
		LifecycleEvent updatedLifecycleEvent = new LifecycleEvent();
		doThrow(new LifecycleEventNotFoundException("Lifecycle event not found")).when(adminService)
				.updateLifecycleEvent(updatedLifecycleEvent);
		ResponseEntity<String> response = adminController.updateLifecycleEvent(updatedLifecycleEvent);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Lifecycle event not found", response.getBody());
	}

	@Test
	void testDeleteLifecycleEvent_Success() throws LifecycleEventNotFoundException {
		Long eventId = 1L;
		ResponseEntity<String> response = adminController.deleteLifecycleEvent(eventId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Lifecycle event deleted successfully", response.getBody());
		verify(adminService).deleteLifecycleEvent(eventId);
	}

	@Test
	void testDeleteLifecycleEvent_LifecycleEventNotFoundException() throws LifecycleEventNotFoundException {
		Long eventId = 1L;
		doThrow(new LifecycleEventNotFoundException("Lifecycle event not found")).when(adminService)
				.deleteLifecycleEvent(eventId);
		ResponseEntity<String> response = adminController.deleteLifecycleEvent(eventId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Lifecycle event not found", response.getBody());
	}

	@Test
	void testUpdateUserRole_Success() throws UserNotFoundException {
		Long userId = 1L;
		Role role = new Role();
		when(userService.updateRole(userId, role)).thenReturn("Role updated successfully");
		ResponseEntity<String> response = adminController.updateUserRole(userId, role);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Role updated successfully", response.getBody());
		verify(userService).updateRole(userId, role);
	}

	@Test
	void testUpdateUserRole_UserNotFoundException() throws UserNotFoundException {
		Long userId = 1L;
		Role role = new Role();
		doThrow(new UserNotFoundException("User not found")).when(userService).updateRole(userId, role);
		ResponseEntity<String> response = adminController.updateUserRole(userId, role);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("User not found", response.getBody());
	}

	@Test
	void testUpdateUserRole_InternalServerError() throws UserNotFoundException {
		Long userId = 1L;
		Role role = new Role();
		doThrow(new RuntimeException()).when(userService).updateRole(userId, role);
		ResponseEntity<String> response = adminController.updateUserRole(userId, role);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("An error occurred while updating the user role.", response.getBody());
	}

	@Test
	void testViewDevices_Success() {
		List<Device> devices = new ArrayList<>();
		devices.add(new Device());
		try {
			when(adminService.viewDevices()).thenReturn(ResponseEntity.ok(devices));
		} catch (DeviceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResponseEntity<?> response = adminController.viewDevices();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(devices, response.getBody());
	}

	@Test
	void testViewDevices_DeviceNotFoundException() throws DeviceNotFoundException {
		doThrow(new DeviceNotFoundException("Devices not found")).when(adminService).viewDevices();
		ResponseEntity<?> response = adminController.viewDevices();
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Devices not found", response.getBody());
	}
	
	 @Test
	    void testSearchDevicesById_Success() {
	        // Arrange
	        Long deviceId = 1L;
	        Device device = new Device();
	        ResponseEntity<Device> expectedResponseEntity = ResponseEntity.ok(device);
	        try {
				when(adminService.searchDevicesById(deviceId)).thenReturn(expectedResponseEntity);
			} catch (DeviceNotFoundException e) {
			
				e.printStackTrace();
			}

	        // Act
	        ResponseEntity<?> response = adminController.searchDevicesById(deviceId);

	        // Assert
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(device, response.getBody());
	        try {
				verify(adminService).searchDevicesById(deviceId);
			} catch (DeviceNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	@Test
	void testSearchDevicesById_DeviceNotFoundException() throws DeviceNotFoundException {
		Long deviceId = 1L;
		doThrow(new DeviceNotFoundException("Device not found")).when(adminService).searchDevicesById(deviceId);
		ResponseEntity<?> response = adminController.searchDevicesById(deviceId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Device not found", response.getBody());
	}

}
