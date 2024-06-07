package com.training.liscenselifecycletracker.service;

public interface ManagementService {
	
	public void overseeLifecycle(Long assetId);

	public void generateLifecycleReports(Long assetId);

}
