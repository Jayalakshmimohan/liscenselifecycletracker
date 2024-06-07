package com.training.liscenselifecycletracker.service;

import java.util.List;

public interface TechnicalSupportService {
	public void logFault(Long deviceId, String description, String date, String category);

	public void updateFaultLog(Long deviceId, String repairDetails, String category, String eventType);

	List<String> viewEndOfSupportDates();


}
