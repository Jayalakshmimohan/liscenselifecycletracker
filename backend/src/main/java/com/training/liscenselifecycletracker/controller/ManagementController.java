package com.training.liscenselifecycletracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.liscenselifecycletracker.service.ManagementService;

@RestController
@RequestMapping("/api/management")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ManagementController {

    @Autowired 
    ManagementService managementService;

    @GetMapping("/lifecycle")
    public ResponseEntity<String> overseeLifecycle(@RequestParam Long assetId) {
        managementService.overseeLifecycle(assetId);
        return ResponseEntity.ok("Overseeing lifecycle for asset with ID: " + assetId);
    }

    @GetMapping("/reports/{assetId}")
    public ResponseEntity<String> generateLifecycleReports(@PathVariable Long assetId) {
        managementService.generateLifecycleReports(assetId);
        return ResponseEntity.ok("Generating reports for asset with ID: " + assetId);
    }
}
