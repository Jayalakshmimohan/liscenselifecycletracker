package com.training.liscenselifecycletracker.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.training.liscenselifecycletracker.entities.Device;
import com.training.liscenselifecycletracker.entities.LifecycleEvent;
import com.training.liscenselifecycletracker.entities.Software;
import com.training.liscenselifecycletracker.entities.User;
import com.training.liscenselifecycletracker.exceptions.DeviceNotFoundException;
import com.training.liscenselifecycletracker.exceptions.LifecycleEventNotFoundException;
import com.training.liscenselifecycletracker.exceptions.SoftwareNotFoundException;
import com.training.liscenselifecycletracker.exceptions.UserNotFoundException;

public interface AdminService {

    void addDevice(Device device);

    void updateDevice(Device updatedDevice) throws DeviceNotFoundException;

    void deleteDevice(Long deviceId) throws DeviceNotFoundException;

    void addSoftware(Software software);

    void updateSoftware(Software updatedSoftware) throws SoftwareNotFoundException;

    void deleteSoftware(Long softwareId) throws SoftwareNotFoundException;

    void addUser(User user);

    void updateUser(User updatedUser) throws UserNotFoundException;

    void deleteUser(Long userId) throws UserNotFoundException;

    void generateReports(String criteria);
    
    void addLifecycleEvent(LifecycleEvent lifecycleEvent);
    
    void updateLifecycleEvent(LifecycleEvent updatedLifecycleEvent) throws LifecycleEventNotFoundException;
    
    void deleteLifecycleEvent(Long eventId) throws LifecycleEventNotFoundException;
    
    ResponseEntity<List<Device>> viewDevices() throws DeviceNotFoundException;
    ResponseEntity<Device> searchDevicesById(Long deviceId) throws DeviceNotFoundException;
}
