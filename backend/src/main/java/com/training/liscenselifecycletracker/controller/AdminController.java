package com.training.liscenselifecycletracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.training.liscenselifecycletracker.service.RegularUser;
import com.training.liscenselifecycletracker.service.UserService;

@RestController
@RequestMapping("/api/admin")
// add cross origin in all controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

    @Autowired
    AdminService adminService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    RegularUser regularUser;

    @PostMapping("/adddevices")
    public ResponseEntity<String> addDevice(@RequestBody Device device) {
        adminService.addDevice(device);
        return ResponseEntity.status(HttpStatus.CREATED).body("Device added successfully");
    }

    @PutMapping("/updatedevices")
    public ResponseEntity<String> updateDevice(@RequestBody Device updatedDevice) {
        try {
            adminService.updateDevice(updatedDevice);
            return ResponseEntity.ok("Device updated successfully");
        } catch (DeviceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/devices/deletedevices")
    public ResponseEntity<String> deleteDevice(@RequestParam Long deviceId) {
        try {
            adminService.deleteDevice(deviceId);
            return ResponseEntity.ok("Device deleted successfully");
        } catch (DeviceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/licenses/addsoftwares")
    public ResponseEntity<String> addSoftware(@RequestBody Software software) {
        adminService.addSoftware(software);
        return ResponseEntity.status(HttpStatus.CREATED).body("Software added successfully");
    }

    @PutMapping("/licenses/updatesoftwares")
    public ResponseEntity<String> updateSoftware(@RequestBody Software updatedSoftware) {
        try {
            adminService.updateSoftware(updatedSoftware);
            return ResponseEntity.ok("Software updated successfully");
        } catch (SoftwareNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/licenses/softwares/deletesoftware")
    public ResponseEntity<String> deleteSoftware(@RequestParam Long softwareId) {
        try {
            adminService.deleteSoftware(softwareId);
            return ResponseEntity.ok("Software deleted successfully");
        } catch (SoftwareNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/reports")
    public ResponseEntity<String> generateReports(@RequestParam String criteria) {
        // Call corresponding service method
        // Placeholder return
        return ResponseEntity.ok("Reports generated successfully");
    }

    @PostMapping("/users/adduser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        adminService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
    }

    @PutMapping("/users/updateuser")
    public ResponseEntity<String> updateUser(@RequestBody User updatedUser) {
        try {
            adminService.updateUser(updatedUser);
            return ResponseEntity.ok("User updated successfully");
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/users/deleteuser")
    public ResponseEntity<String> deleteUser(@RequestParam Long userId) {
        try {
            adminService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
    
    @PostMapping("/lifecycleevents/add")
    public ResponseEntity<String> addLifecycleEvent(@RequestBody LifecycleEvent lifecycleEvent) {
        adminService.addLifecycleEvent(lifecycleEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body("Lifecycle event added successfully");
    }

    @PutMapping("/lifecycleevents/update")
    public ResponseEntity<String> updateLifecycleEvent(@RequestBody LifecycleEvent updatedLifecycleEvent) {
        try {
            adminService.updateLifecycleEvent(updatedLifecycleEvent);
            return ResponseEntity.ok("Lifecycle event updated successfully");
        } catch (LifecycleEventNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/lifecycleevents/delete")
    public ResponseEntity<String> deleteLifecycleEvent(@RequestParam Long eventId) {
        try {
            adminService.deleteLifecycleEvent(eventId);
            return ResponseEntity.ok("Lifecycle event deleted successfully");
        } catch (LifecycleEventNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
    
    
    @PutMapping("/users/updaterole")
    public ResponseEntity<String> updateUserRole(@RequestParam Long userId, @RequestBody Role role) {
        try {
            String result = userService.updateRole(userId, role);
            return ResponseEntity.ok(result);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the user role.");
        }
    }
    
    @GetMapping("/getalldevices")
    public ResponseEntity<?> viewDevices() {
        try {
            List<Device> devices = (List<Device>) adminService.viewDevices().getBody();
            return ResponseEntity.ok(devices);
        } catch (DeviceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/devices/searchById")
    public ResponseEntity<?> searchDevicesById(@RequestParam Long deviceId) {
        try {
            Device devices =  adminService.searchDevicesById(deviceId).getBody();
            return ResponseEntity.ok(devices);
        } catch (DeviceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
