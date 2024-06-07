package com.training.liscenselifecycletracker.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.liscenselifecycletracker.entities.Device;
import com.training.liscenselifecycletracker.entities.Software;
import com.training.liscenselifecycletracker.exceptions.DeviceNotFoundException;
import com.training.liscenselifecycletracker.exceptions.SoftwareNotFoundException;
import com.training.liscenselifecycletracker.repositories.DeviceRepository;
import com.training.liscenselifecycletracker.repositories.SoftwareRepository;

@Service
public class RegularUserServiceImpl implements RegularUser {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private SoftwareRepository softwareRepository;

    @Override
    public ResponseEntity<List<Device>> viewDevices() throws DeviceNotFoundException {
        List<Device> devices = (List<Device>) deviceRepository.findAll();
        if (devices.isEmpty()) {
            throw new DeviceNotFoundException("No devices found.");
        }
        return ResponseEntity.ok(devices);
    }

    @Override
    public ResponseEntity<List<String>> receiveNotifications() {
        List<Software> expiringSoftware = retrieveExpiringSoftware();

        List<String> notificationMessages = new ArrayList<>();

        for (Software software : expiringSoftware) {
            LocalDate expiryDate = software.getExpiryDate();
            if (LocalDate.now().plusDays(30).isAfter(expiryDate)) {
                String message = "Your software license (ID: " + software.getSoftwareId() + ") is expiring soon. Renew now to avoid service disruptions.";
                notificationMessages.add(message);
            }
        }

        if (notificationMessages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonList("No expiring software licenses found."));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(notificationMessages);
        }
    }
    
	 // Method to retrieve expiring software from the database
	    private List<Software> retrieveExpiringSoftware() {
	        LocalDate thirtyDaysFromNow = LocalDate.now().plusDays(30);
	        return softwareRepository.findByExpiryDate(thirtyDaysFromNow);
	    }

    @Override
    public void searchAssets(String keyword) {
        
    }

    @Override
    public ResponseEntity<Device> searchDevicesById(Long deviceId) throws DeviceNotFoundException {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException("Device with ID " + deviceId + " not found."));
        return ResponseEntity.ok(device);
    }

    @Override
    public ResponseEntity<List<Device>> searchDevicesByName(String deviceName) throws DeviceNotFoundException {
        List<Device> devices = deviceRepository.findByDeviceName(deviceName);
        if (devices.isEmpty()) {
            throw new DeviceNotFoundException("Device with name '" + deviceName + "' not found.");
        }
        return ResponseEntity.ok(devices);
    }

    @Override
    public ResponseEntity<List<Device>> searchDevicesByType(String deviceType) throws DeviceNotFoundException {
        List<Device> devices = deviceRepository.findByDeviceType(deviceType);
        if (devices.isEmpty()) {
            throw new DeviceNotFoundException("No devices found for type '" + deviceType + "'.");
        }
        return ResponseEntity.ok(devices);
    }

    @Override
    public ResponseEntity<List<Device>> searchDevicesByPurchaseDate(LocalDate purchaseDate) throws DeviceNotFoundException {
        List<Device> devices = deviceRepository.findByPurchaseDate(purchaseDate);
        if (devices.isEmpty()) {
            throw new DeviceNotFoundException("No devices found for purchase date '" + purchaseDate + "'.");
        }
        return ResponseEntity.ok(devices);
    }

    @Override
    public ResponseEntity<List<Device>> searchDevicesByExpirationDate(LocalDate expirationDate) throws DeviceNotFoundException {
        List<Device> devices = deviceRepository.findByExpirationDate(expirationDate);
        if (devices.isEmpty()) {
            throw new DeviceNotFoundException("No devices found for expiration date '" + expirationDate + "'.");
        }
        return ResponseEntity.ok(devices);
    }

    @Override
    public ResponseEntity<List<Device>> searchDevicesByStatus(String status) throws DeviceNotFoundException {
        List<Device> devices = deviceRepository.findByStatus(status);
        if (devices.isEmpty()) {
            throw new DeviceNotFoundException("No devices found for status '" + status + "'.");
        }
        return ResponseEntity.ok(devices);
    }

    @Override
    public ResponseEntity<List<Device>> searchDevicesBySupportEndDate(LocalDate supportEndDate) throws DeviceNotFoundException {
        List<Device> devices = deviceRepository.findBySupportEndDate(supportEndDate);
        if (devices.isEmpty()) {
            throw new DeviceNotFoundException("No devices found for support end date '" + supportEndDate + "'.");
        }
        return ResponseEntity.ok(devices);
    }

    @Override
    public ResponseEntity<List<Software>> searchSoftwareById(Long softwareId) throws SoftwareNotFoundException {
        List<Software> softwareList = softwareRepository.findBySoftwareId(softwareId);
        if (softwareList.isEmpty()) {
            throw new SoftwareNotFoundException("Software with ID " + softwareId + " not found.");
        }
        return ResponseEntity.ok(softwareList);
    }

    @Override
    public ResponseEntity<List<Software>> searchSoftwareByName(String softwareName) throws SoftwareNotFoundException {
        List<Software> softwareList = softwareRepository.findBySoftwareName(softwareName);
        if (softwareList.isEmpty()) {
            throw new SoftwareNotFoundException("Software with name '" + softwareName + "' not found.");
        }
        return ResponseEntity.ok(softwareList);
    }



    @Override
    public ResponseEntity<List<Software>> searchSoftwareByPurchaseDate(LocalDate purchaseDate) throws SoftwareNotFoundException {
        List<Software> softwareList = softwareRepository.findByPurchaseDate(purchaseDate);
        if (softwareList.isEmpty()) {
            throw new SoftwareNotFoundException("No software found for purchase date '" + purchaseDate + "'.");
        }
        return ResponseEntity.ok(softwareList);
    }

    @Override
    public ResponseEntity<List<Software>> searchSoftwareByExpiryDate(LocalDate expiryDate) throws SoftwareNotFoundException {
        List<Software> softwareList = softwareRepository.findByExpiryDate(expiryDate);
        if (softwareList.isEmpty()) {
            throw new SoftwareNotFoundException("No software found for expiry date '" + expiryDate + "'.");
        }
        return ResponseEntity.ok(softwareList);
    }

    @Override
    public ResponseEntity<List<Software>> searchSoftwareBySupportEndDate(LocalDate supportEndDate) throws SoftwareNotFoundException {
        List<Software> softwareList = softwareRepository.findBySupportEndDate(supportEndDate);
        if (softwareList.isEmpty()) {
            throw new SoftwareNotFoundException("No software found for support end date '" + supportEndDate + "'.");
        }
        return ResponseEntity.ok(softwareList);
    }
}
