package com.training.liscenselifecycletracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.liscenselifecycletracker.entities.LifecycleEvent;
import com.training.liscenselifecycletracker.repositories.LifecycleEventRepository;

@Service
public class ManagementServiceImpl implements ManagementService {

    @Autowired
    LifecycleEventRepository lifecycleEventRepository;

    @Override
    public void overseeLifecycle(Long assetId) {
        // Fetch lifecycle events for the given assetId
        LifecycleEvent events = lifecycleEventRepository.findByAssetId(assetId);

        // Perform management actions on the fetched lifecycle events
        if (events != null) {
            // Example: Log lifecycle events or perform actions based on event type
            System.out.println("Lifecycle event: " + events.getEventType());
        } else {
            System.out.println("No lifecycle events found for asset with ID: " + assetId);
        }
    }

    @Override
    public void generateLifecycleReports(Long assetId) {
        // Fetch lifecycle events for the given assetId
        LifecycleEvent events = lifecycleEventRepository.findByAssetId(assetId);

        // Generate reports based on the fetched lifecycle events
        if (events != null) {
            // Example: Generate reports based on event details
            System.out.println("Generating report for lifecycle event: " + events);
        } else {
            System.out.println("No lifecycle events found for asset with ID: " + assetId);
        }
    }
}
