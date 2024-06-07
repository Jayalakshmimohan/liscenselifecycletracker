package com.training.liscenselifecycletracker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import com.training.liscenselifecycletracker.entities.Software;
import com.training.liscenselifecycletracker.exceptions.DeviceNotFoundException;
import com.training.liscenselifecycletracker.exceptions.SoftwareNotFoundException;
import com.training.liscenselifecycletracker.service.RegularUser;

@ExtendWith(MockitoExtension.class)
public class RegularUserControllerTest {

    @Mock
    private RegularUser regularUser;

    @InjectMocks
    private RegularUserController regularUserController;

    @Test
    public void testSearchDevicesById_Success() throws DeviceNotFoundException {
        // Mock data
        Device device = new Device(1L, "Device1", "Type1", LocalDate.now(), LocalDate.now(), LocalDate.now(), "Active");

        // Mock method call
        when(regularUser.searchDevicesById(1L)).thenReturn(ResponseEntity.ok(device));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchDevicesById(1L);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(device, response.getBody());
    }

    @Test
    public void testSearchDevicesById_DeviceNotFoundException() throws DeviceNotFoundException {
        // Mock method call
        when(regularUser.searchDevicesById(1L)).thenThrow(new DeviceNotFoundException("Device not found"));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchDevicesById(1L);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Device not found", response.getBody());
    }

    // Similar tests for other controller methods
    
    @Test
    public void testSearchSoftwareById_Success() throws SoftwareNotFoundException {
        // Mock data
        Software software = new Software(1L, "Software1", "Key1", LocalDate.now(), LocalDate.now(), LocalDate.now(), "Active");

        // Mock method call
        when(regularUser.searchSoftwareById(1L)).thenReturn(ResponseEntity.ok(List.of(software)));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchSoftwareById(1L);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(software), response.getBody());
    }

    @Test
    public void testSearchSoftwareById_SoftwareNotFoundException() throws SoftwareNotFoundException {
        // Mock method call
        when(regularUser.searchSoftwareById(1L)).thenThrow(new SoftwareNotFoundException("Software not found"));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchSoftwareById(1L);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Software not found", response.getBody());
    }

    @Test
    public void testSearchSoftwareByName_Success() throws SoftwareNotFoundException {
        // Mock data
        Software software = new Software(1L, "Software1", "Key1", LocalDate.now(), LocalDate.now(), LocalDate.now(), "Active");

        // Mock method call
        when(regularUser.searchSoftwareByName("Software1")).thenReturn(ResponseEntity.ok(List.of(software)));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchSoftwareByName("Software1");

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(software), response.getBody());
    }

    @Test
    public void testSearchSoftwareByName_SoftwareNotFoundException() throws SoftwareNotFoundException {
        // Mock method call
        when(regularUser.searchSoftwareByName("Software1")).thenThrow(new SoftwareNotFoundException("Software not found"));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchSoftwareByName("Software1");

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Software not found", response.getBody());
    }

    @Test
    public void testSearchSoftwareByPurchaseDate_Success() throws SoftwareNotFoundException {
        // Mock data
        LocalDate purchaseDate = LocalDate.now();
        Software software = new Software(1L, "Software1", "Key1", purchaseDate, LocalDate.now(), LocalDate.now(), "Active");

        // Mock method call
        when(regularUser.searchSoftwareByPurchaseDate(purchaseDate)).thenReturn(ResponseEntity.ok(List.of(software)));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchSoftwareByPurchaseDate(purchaseDate);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(software), response.getBody());
    }

    @Test
    public void testSearchSoftwareByPurchaseDate_SoftwareNotFoundException() throws SoftwareNotFoundException {
        // Mock method call
        LocalDate purchaseDate = LocalDate.now();
        when(regularUser.searchSoftwareByPurchaseDate(purchaseDate)).thenThrow(new SoftwareNotFoundException("Software not found"));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchSoftwareByPurchaseDate(purchaseDate);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Software not found", response.getBody());
    }
    @Test
    public void testSearchSoftwareByExpiryDate_Success() throws SoftwareNotFoundException {
        // Mock data
        LocalDate expiryDate = LocalDate.now();
        Software software = new Software(1L, "Software1", "Key1", LocalDate.now(), expiryDate, LocalDate.now(), "Active");

        // Mock method call
        when(regularUser.searchSoftwareByExpiryDate(expiryDate)).thenReturn(ResponseEntity.ok(List.of(software)));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchSoftwareByExpiryDate(expiryDate);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(software), response.getBody());
    }

    @Test
    public void testSearchSoftwareByExpiryDate_SoftwareNotFoundException() throws SoftwareNotFoundException {
        // Mock method call
        LocalDate expiryDate = LocalDate.now();
        when(regularUser.searchSoftwareByExpiryDate(expiryDate)).thenThrow(new SoftwareNotFoundException("Software not found"));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchSoftwareByExpiryDate(expiryDate);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Software not found", response.getBody());
    }

    @Test
    public void testSearchSoftwareBySupportEndDate_Success() throws SoftwareNotFoundException {
        // Mock data
        LocalDate supportEndDate = LocalDate.now();
        Software software = new Software(1L, "Software1", "Key1", LocalDate.now(), LocalDate.now(), supportEndDate, "Active");

        // Mock method call
        when(regularUser.searchSoftwareBySupportEndDate(supportEndDate)).thenReturn(ResponseEntity.ok(List.of(software)));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchSoftwareBySupportEndDate(supportEndDate);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(software), response.getBody());
    }

    @Test
    public void testSearchSoftwareBySupportEndDate_SoftwareNotFoundException() throws SoftwareNotFoundException {
        // Mock method call
        LocalDate supportEndDate = LocalDate.now();
        when(regularUser.searchSoftwareBySupportEndDate(supportEndDate)).thenThrow(new SoftwareNotFoundException("Software not found"));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchSoftwareBySupportEndDate(supportEndDate);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Software not found", response.getBody());
    }
    @Test
    public void testSearchDevicesByName_Success() throws DeviceNotFoundException {
        // Mock data
        List<Device> devices = new ArrayList<>();
        devices.add(new Device(1L, "Device1", "Type1", LocalDate.now(), LocalDate.now(), LocalDate.now(), "Active"));

        // Mock method call
        when(regularUser.searchDevicesByName("Device1")).thenReturn(ResponseEntity.ok(devices));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchDevicesByName("Device1");

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(devices, response.getBody());
    }

    @Test
    public void testSearchDevicesByName_DeviceNotFoundException() throws DeviceNotFoundException {
        // Mock method call
        when(regularUser.searchDevicesByName("Device1")).thenThrow(new DeviceNotFoundException("Device not found"));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchDevicesByName("Device1");

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Device not found", response.getBody());
    }

    @Test
    public void testSearchDevicesByType_Success() throws DeviceNotFoundException {
        // Mock data
        List<Device> devices = new ArrayList<>();
        devices.add(new Device(1L, "Device1", "Type1", LocalDate.now(), LocalDate.now(), LocalDate.now(), "Active"));

        // Mock method call
        when(regularUser.searchDevicesByType("Type1")).thenReturn(ResponseEntity.ok(devices));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchDevicesByType("Type1");

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(devices, response.getBody());
    }

    @Test
    public void testSearchDevicesByType_DeviceNotFoundException() throws DeviceNotFoundException {
        // Mock method call
        when(regularUser.searchDevicesByType("Type1")).thenThrow(new DeviceNotFoundException("Device not found"));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchDevicesByType("Type1");

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Device not found", response.getBody());
    }

    @Test
    public void testSearchDevicesByPurchaseDate_Success() throws DeviceNotFoundException {
        // Mock data
        LocalDate purchaseDate = LocalDate.now();
        List<Device> devices = new ArrayList<>();
        devices.add(new Device(1L, "Device1", "Type1", purchaseDate, LocalDate.now(), LocalDate.now(), "Active"));

        // Mock method call
        when(regularUser.searchDevicesByPurchaseDate(purchaseDate)).thenReturn(ResponseEntity.ok(devices));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchDevicesByPurchaseDate(purchaseDate);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(devices, response.getBody());
    }

    @Test
    public void testSearchDevicesByPurchaseDate_DeviceNotFoundException() throws DeviceNotFoundException {
        // Mock method call
        LocalDate purchaseDate = LocalDate.now();
        when(regularUser.searchDevicesByPurchaseDate(purchaseDate)).thenThrow(new DeviceNotFoundException("Device not found"));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchDevicesByPurchaseDate(purchaseDate);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Device not found", response.getBody());
    }

    @Test
    public void testSearchDevicesByExpirationDate_Success() throws DeviceNotFoundException {
        // Mock data
        LocalDate expirationDate = LocalDate.now();
        List<Device> devices = new ArrayList<>();
        devices.add(new Device(1L, "Device1", "Type1", LocalDate.now(), expirationDate, LocalDate.now(), "Active"));

        // Mock method call
        when(regularUser.searchDevicesByExpirationDate(expirationDate)).thenReturn(ResponseEntity.ok(devices));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchDevicesByExpirationDate(expirationDate);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(devices, response.getBody());
    }

    @Test
    public void testSearchDevicesByExpirationDate_DeviceNotFoundException() throws DeviceNotFoundException {
        // Mock method call
        LocalDate expirationDate = LocalDate.now();
        when(regularUser.searchDevicesByExpirationDate(expirationDate)).thenThrow(new DeviceNotFoundException("Device not found"));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchDevicesByExpirationDate(expirationDate);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Device not found", response.getBody());
    }

    @Test
    public void testSearchDevicesBySupportEndDate_Success() throws DeviceNotFoundException {
        // Mock data
        LocalDate supportEndDate = LocalDate.now();
        List<Device> devices = new ArrayList<>();
        devices.add(new Device(1L, "Device1", "Type1", LocalDate.now(), LocalDate.now(), supportEndDate, "Active"));

        // Mock method call
        when(regularUser.searchDevicesBySupportEndDate(supportEndDate)).thenReturn(ResponseEntity.ok(devices));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchDevicesBySupportEndDate(supportEndDate);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(devices, response.getBody());
    }

    @Test
    public void testSearchDevicesBySupportEndDate_DeviceNotFoundException() throws DeviceNotFoundException {
        // Mock method call
        LocalDate supportEndDate = LocalDate.now();
        when(regularUser.searchDevicesBySupportEndDate(supportEndDate)).thenThrow(new DeviceNotFoundException("Device not found"));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchDevicesBySupportEndDate(supportEndDate);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Device not found", response.getBody());
    }

    @Test
    public void testSearchDevicesByStatus_Success() throws DeviceNotFoundException {
        // Mock data
        List<Device> devices = new ArrayList<>();
        devices.add(new Device(1L, "Device1", "Type1", LocalDate.now(), LocalDate.now(), LocalDate.now(), "Active"));

        // Mock method call
        when(regularUser.searchDevicesByStatus("Active")).thenReturn(ResponseEntity.ok(devices));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchDevicesByStatus("Active");

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(devices, response.getBody());
    }

    @Test
    public void testSearchDevicesByStatus_DeviceNotFoundException() throws DeviceNotFoundException {
        // Mock method call
        when(regularUser.searchDevicesByStatus("Active")).thenThrow(new DeviceNotFoundException("Device not found"));

        // Test the controller method
        ResponseEntity<?> response = regularUserController.searchDevicesByStatus("Active");

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Device not found", response.getBody());
    }

  
    @Test
    public void testReceiveNotifications_Success() {
        // Mock data
        List<String> notifications = List.of("Notification 1", "Notification 2");

        // Mock method call
        when(regularUser.receiveNotifications()).thenReturn(ResponseEntity.ok(notifications));

        // Test the controller method
        ResponseEntity<List<String>> response = regularUserController.receiveNotifications();

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(notifications, response.getBody());
    }

    @Test
    public void testSearchAssets_Success() {
        // Mock method call
        ResponseEntity<?> response = regularUserController.searchAssets("keyword");

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }



    

}
