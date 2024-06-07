package com.training.liscenselifecycletracker.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.training.liscenselifecycletracker.entities.Device;
import com.training.liscenselifecycletracker.entities.Software;
import com.training.liscenselifecycletracker.exceptions.DeviceNotFoundException;
import com.training.liscenselifecycletracker.exceptions.SoftwareNotFoundException;

public interface RegularUser {
    ResponseEntity<List<Device>> viewDevices() throws DeviceNotFoundException;
    ResponseEntity<List<String>> receiveNotifications();
    void searchAssets(String keyword);

    // Search methods for Device
    ResponseEntity<Device> searchDevicesById(Long deviceId) throws DeviceNotFoundException;
    ResponseEntity<List<Device>> searchDevicesByName(String deviceName) throws DeviceNotFoundException;
    ResponseEntity<List<Device>> searchDevicesByType(String deviceType) throws DeviceNotFoundException;
    ResponseEntity<List<Device>> searchDevicesByPurchaseDate(LocalDate purchaseDate) throws DeviceNotFoundException;
    ResponseEntity<List<Device>> searchDevicesByExpirationDate(LocalDate expirationDate) throws DeviceNotFoundException;
    ResponseEntity<List<Device>> searchDevicesByStatus(String status) throws DeviceNotFoundException;
    ResponseEntity<List<Device>> searchDevicesBySupportEndDate(LocalDate supportEndDate) throws DeviceNotFoundException;

    // Search methods for Software
    ResponseEntity<List<Software>> searchSoftwareById(Long softwareId) throws SoftwareNotFoundException;
    ResponseEntity<List<Software>> searchSoftwareByName(String softwareName) throws SoftwareNotFoundException;
    ResponseEntity<List<Software>> searchSoftwareByPurchaseDate(LocalDate purchaseDate) throws SoftwareNotFoundException;
    ResponseEntity<List<Software>> searchSoftwareByExpiryDate(LocalDate expiryDate) throws SoftwareNotFoundException;
    ResponseEntity<List<Software>> searchSoftwareBySupportEndDate(LocalDate supportEndDate) throws SoftwareNotFoundException;
}
