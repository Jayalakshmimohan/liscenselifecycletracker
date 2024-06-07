package com.training.liscenselifecycletracker.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.liscenselifecycletracker.entities.Device;
import com.training.liscenselifecycletracker.entities.LifecycleEvent;
import com.training.liscenselifecycletracker.entities.Software;
import com.training.liscenselifecycletracker.repositories.DeviceRepository;
import com.training.liscenselifecycletracker.repositories.LifecycleEventRepository;
import com.training.liscenselifecycletracker.repositories.SoftwareRepository;

@Service
public class TechnicalSupportServiceImpl implements TechnicalSupportService {
    
    @Autowired
    DeviceRepository deviceRepository;
    
    @Autowired
    SoftwareRepository softwareRepository;
    
    @Autowired
    LifecycleEventRepository lifecycleEventRepository;

    @Override
    public void logFault(Long deviceId, String description, String date, String category) {
        // Fetch the device from the repository
        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);

        if (optionalDevice.isEmpty()) {
            throw new IllegalArgumentException("Device not found with ID: " + deviceId);
        }

        Device device = optionalDevice.get();
        
       LifecycleEvent faultEvent =lifecycleEventRepository.findByAssetId(deviceId);

        // Create a new lifecycle event for the fault
//        LifecycleEvent faultEvent = new LifecycleEvent();
       	if(faultEvent != null) {
       		faultEvent.setEventType("Audit");
            faultEvent.setDescription(description);
            faultEvent.setEventDate(LocalDate.parse(date));
            
       		
       	}
       	else {
       		
       		faultEvent = new LifecycleEvent();
            faultEvent.setAssetId(deviceId);
            faultEvent.setEventType("Audit");
            faultEvent.setDescription(description);
            faultEvent.setCategory(category);
            faultEvent.setEventDate(LocalDate.parse(date));
       		
       	}
        

        // Save the fault event
        lifecycleEventRepository.save(faultEvent);
    }

    @Override
    public void updateFaultLog(Long deviceId, String repairDetails, String category, String eventType) {
        // Fetch the device from the repository
        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);

        if (optionalDevice.isEmpty()) {
            throw new IllegalArgumentException("Device not found with ID: " + deviceId);
        }

        Device device = optionalDevice.get();

        // Find the existing fault log for the device
        Optional<LifecycleEvent> optionalExistingFaultEvent = lifecycleEventRepository.findByAssetIdAndEventType(deviceId, eventType);

        if (!optionalExistingFaultEvent.isPresent()) {
            throw new IllegalStateException("No existing fault log found for device ID: " + deviceId);
        }

        LifecycleEvent existingFaultEvent = optionalExistingFaultEvent.get();

        // Update the fault log with repair details
//        existingFaultEvent.setAssetId(deviceId);
        existingFaultEvent.setEventType("Resolved");
        existingFaultEvent.setDescription(repairDetails);
        existingFaultEvent.setCategory(category);

        // Save the updated fault log
        lifecycleEventRepository.save(existingFaultEvent);
    }

    @Override
    public List<String> viewEndOfSupportDates() {
        List<String> supportDates = new ArrayList<>();

        // Fetch end of support dates for devices
        List<Device> devices = (List<Device>) deviceRepository.findAll();
        for (Device device : devices) {
            String supportInfo = "Asset ID: " + device.getDeviceId() +
                                 ", Asset Name: " + device.getDeviceName() +
                                 ", End of Support Date: " + device.getSupportEndDate().format(DateTimeFormatter.ISO_DATE);
            supportDates.add(supportInfo);
        }

        // Fetch end of support dates for software
        List<Software> software = (List<Software>) softwareRepository.findAll();
        for (Software s : software) {
            String supportInfo = "Asset ID: " + s.getSoftwareId() +
                                 ", Asset Name: " + s.getSoftwareName() +
                                 ", End of Support Date: " + s.getSupportEndDate().format(DateTimeFormatter.ISO_DATE);
            supportDates.add(supportInfo);
        }

        return supportDates;
    }
}