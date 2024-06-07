package com.training.liscenselifecycletracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.liscenselifecycletracker.service.TechnicalSupportService;

@RestController
@RequestMapping("/api/technicalsupport")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TechnicalSupportController {
    
    @Autowired 
    TechnicalSupportService technicalSupportService;

    @PostMapping("/support/faults")
    public void logFault(@RequestParam Long deviceId, @RequestParam String description, @RequestParam String date, @RequestParam String category) {
        technicalSupportService.logFault(deviceId, description, date, category);
    }

    @PutMapping("/support/faults/deviceId")
    public void updateFaultLog(@RequestParam Long deviceId, @RequestParam String repairDetails, @RequestParam String category, @RequestParam String eventType) {
        technicalSupportService.updateFaultLog(deviceId, repairDetails, category, eventType);
    }

    @GetMapping("/support/dates")
    public List<String> viewEndOfSupportDates() {
        return technicalSupportService.viewEndOfSupportDates();
    }
}
