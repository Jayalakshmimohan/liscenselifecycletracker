package com.training.liscenselifecycletracker.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.liscenselifecycletracker.entities.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
		List<Device> findByDeviceId(Long deviceId);
	    List<Device> findByDeviceName(String deviceName);
	    List<Device> findByDeviceType(String deviceType);
	    List<Device> findByPurchaseDate(LocalDate purchaseDate);
	    List<Device> findByExpirationDate(LocalDate expirationDate);
	    List<Device> findByStatus(String status);
	    List<Device> findBySupportEndDate(LocalDate supportEndDate);
}
