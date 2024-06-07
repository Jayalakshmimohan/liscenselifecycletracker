package com.training.liscenselifecycletracker.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.liscenselifecycletracker.entities.Device;
import com.training.liscenselifecycletracker.entities.Software;
import com.training.liscenselifecycletracker.exceptions.DeviceNotFoundException;
import com.training.liscenselifecycletracker.exceptions.SoftwareNotFoundException;
import com.training.liscenselifecycletracker.service.RegularUser;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RegularUserController {
    
    @Autowired
    RegularUser regularUser;
    
    @GetMapping("/devices")
    public ResponseEntity<?> viewDevices() {
        try {
            List<Device> devices = (List<Device>) regularUser.viewDevices().getBody();
            return ResponseEntity.ok(devices);
        } catch (DeviceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/notifications")
    public ResponseEntity<List<String>> receiveNotifications() {
        return regularUser.receiveNotifications();
    }

    @GetMapping("/assets/search")
    public ResponseEntity<?> searchAssets(@RequestParam String keyword) {
        // Call corresponding service method
        regularUser.searchAssets(keyword);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/devices/searchById")
    public ResponseEntity<?> searchDevicesById(@RequestParam Long deviceId) {
        try {
            Device devices = regularUser.searchDevicesById(deviceId).getBody();
            return ResponseEntity.ok(devices);
        } catch (DeviceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/devices/searchByName")
    public ResponseEntity<?> searchDevicesByName(@RequestParam String deviceName) {
        try {
            List<Device> devices = (List<Device>) regularUser.searchDevicesByName(deviceName).getBody();
            return ResponseEntity.ok(devices);
        } catch (DeviceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/devices/searchByType")
    public ResponseEntity<?> searchDevicesByType(@RequestParam String deviceType) {
        try {
            List<Device> devices = (List<Device>) regularUser.searchDevicesByType(deviceType).getBody();
            return ResponseEntity.ok(devices);
        } catch (DeviceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/devices/searchByPurchaseDate")
    public ResponseEntity<?> searchDevicesByPurchaseDate(@RequestParam LocalDate purchaseDate) {
        try {
            List<Device> devices = (List<Device>) regularUser.searchDevicesByPurchaseDate(purchaseDate).getBody();
            return ResponseEntity.ok(devices);
        } catch (DeviceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/devices/searchByExpirationDate")
    public ResponseEntity<?> searchDevicesByExpirationDate(@RequestParam LocalDate expirationDate) {
        try {
            List<Device> devices = (List<Device>) regularUser.searchDevicesByExpirationDate(expirationDate).getBody();
            return ResponseEntity.ok(devices);
        } catch (DeviceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/devices/searchByStatus")
    public ResponseEntity<?> searchDevicesByStatus(@RequestParam String status) {
        try {
            List<Device> devices = (List<Device>) regularUser.searchDevicesByStatus(status).getBody();
            return ResponseEntity.ok(devices);
        } catch (DeviceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/devices/searchBySupportEndDate")
    public ResponseEntity<?> searchDevicesBySupportEndDate(@RequestParam LocalDate supportEndDate) {
        try {
            List<Device> devices = (List<Device>) regularUser.searchDevicesBySupportEndDate(supportEndDate).getBody();
            return ResponseEntity.ok(devices);
        } catch (DeviceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/software/searchById")
    public ResponseEntity<?> searchSoftwareById(@RequestParam Long softwareId) {
        try {
            List<Software> softwareList = (List<Software>) regularUser.searchSoftwareById(softwareId).getBody();
            return ResponseEntity.ok(softwareList);
        } catch (SoftwareNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/software/searchByName")
    public ResponseEntity<?> searchSoftwareByName(@RequestParam String softwareName) {
        try {
            List<Software> softwareList = (List<Software>) regularUser.searchSoftwareByName(softwareName).getBody();
            return ResponseEntity.ok(softwareList);
        } catch (SoftwareNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/software/searchByPurchaseDate")
    public ResponseEntity<?> searchSoftwareByPurchaseDate(@RequestParam LocalDate purchaseDate) {
        try {
            List<Software> softwareList = (List<Software>) regularUser.searchSoftwareByPurchaseDate(purchaseDate).getBody();
            return ResponseEntity.ok(softwareList);
        } catch (SoftwareNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/software/searchByExpiryDate")
    public ResponseEntity<?> searchSoftwareByExpiryDate(@RequestParam LocalDate expiryDate) {
        try {
            List<Software> softwareList = (List<Software>) regularUser.searchSoftwareByExpiryDate(expiryDate).getBody();
            return ResponseEntity.ok(softwareList);
        } catch (SoftwareNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/software/searchBySupportEndDate")
    public ResponseEntity<?> searchSoftwareBySupportEndDate(@RequestParam LocalDate supportEndDate) {
        try {
            List<Software> softwareList = (List<Software>) regularUser.searchSoftwareBySupportEndDate(supportEndDate).getBody();
            return ResponseEntity.ok(softwareList);
        } catch (SoftwareNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
