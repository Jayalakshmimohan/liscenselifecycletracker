package com.training.liscenselifecycletracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    DeviceRepository deviceRepository;
    
    @Autowired
    SoftwareRepository softwareRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    LifecycleEventRepository lifecycleEventRepository;
    

    @Override
    public void addDevice(Device device) {
        deviceRepository.save(device);
    }

    @Override
    public void updateDevice(Device updatedDevice) throws DeviceNotFoundException {
        Long deviceId = updatedDevice.getDeviceId();
        Optional<Device> existingDeviceOptional = deviceRepository.findById(deviceId);
        if (existingDeviceOptional.isPresent()) {
            deviceRepository.save(updatedDevice);
        } else {
            throw new DeviceNotFoundException("Device with ID " + deviceId + " not found");
        }
    }

    @Override
    public void deleteDevice(Long deviceId) throws DeviceNotFoundException {
        Optional<Device> device = deviceRepository.findById(deviceId);
        if (device.isPresent()) {
            deviceRepository.deleteById(deviceId);
        } else {
            throw new DeviceNotFoundException("Device with ID " + deviceId + " not found");
        }
    }

    @Override
    public void addSoftware(Software software) {
        softwareRepository.save(software);
    }

    @Override
    public void updateSoftware(Software updatedSoftware) throws SoftwareNotFoundException {
        Long softwareId = updatedSoftware.getSoftwareId();
        Optional<Software> existingSoftwareOptional = softwareRepository.findById(softwareId);
        if (existingSoftwareOptional.isPresent()) {
            softwareRepository.save(updatedSoftware);
        } else {
            throw new SoftwareNotFoundException("Software with ID " + softwareId + " not found");
        }
    }

    @Override
    public void deleteSoftware(Long softwareId) throws SoftwareNotFoundException {
        Optional<Software> software = softwareRepository.findById(softwareId);
        if (software.isPresent()) {
            softwareRepository.deleteById(softwareId);
        } else {
            throw new SoftwareNotFoundException("Software with ID " + softwareId + " not found");
        }
    }
    
    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User updatedUser) throws UserNotFoundException {
        Long userId = updatedUser.getUserId();
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingUserOptional.isPresent()) {
            userRepository.save(updatedUser);
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

    @Override
    public void deleteUser(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

    @Override
    public void generateReports(String criteria) {
        // Implement generateReports method
    }
    
    @Override
    public void addLifecycleEvent(LifecycleEvent lifecycleEvent) {
        lifecycleEventRepository.save(lifecycleEvent);
    }

    @Override
    public void updateLifecycleEvent(LifecycleEvent updatedLifecycleEvent) throws LifecycleEventNotFoundException {
        Optional<LifecycleEvent> existingLifecycleEvent = lifecycleEventRepository.findById(updatedLifecycleEvent.getEventId());
        if(existingLifecycleEvent.isPresent()) {
            lifecycleEventRepository.save(updatedLifecycleEvent);
        } else {
            throw new LifecycleEventNotFoundException("Lifecycle event with ID " + updatedLifecycleEvent.getEventId() + " not found");
        }
    }


        
    
    @Override
    public void deleteLifecycleEvent(Long eventId) throws LifecycleEventNotFoundException {
        Optional<LifecycleEvent> lifecycleEventOptional = lifecycleEventRepository.findById(eventId);
        if (lifecycleEventOptional.isPresent()) {
            LifecycleEvent lifecycleEvent = lifecycleEventOptional.get();
            lifecycleEventRepository.delete(lifecycleEvent);
        } else {
            throw new LifecycleEventNotFoundException("Lifecycle event with ID " + eventId + " not found");
        }
    }
    
    @Override
    public ResponseEntity<List<Device>> viewDevices() throws DeviceNotFoundException {
        List<Device> devices = (List<Device>) deviceRepository.findAll();
        if (devices.isEmpty()) {
            throw new DeviceNotFoundException("No devices found.");
        }
        return ResponseEntity.ok(devices);
    }
    

    @Override
    public ResponseEntity<Device> searchDevicesById(Long deviceId) throws DeviceNotFoundException {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException("Device with ID " + deviceId + " not found."));
        return ResponseEntity.ok(device);
    }
}
