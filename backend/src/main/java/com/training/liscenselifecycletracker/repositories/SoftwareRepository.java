package com.training.liscenselifecycletracker.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.liscenselifecycletracker.entities.Software;

@Repository
public interface SoftwareRepository extends CrudRepository<Software, Long> {
	 	List<Software> findBySoftwareId(Long softwareId);
	    List<Software> findBySoftwareName(String softwareName);
//	    List<Software> findByLicenseKey(Date licenseKey);
	    List<Software> findByPurchaseDate(LocalDate purchaseDate);
	    List<Software> findByExpiryDate(LocalDate expiryDate);
	    List<Software> findBySupportEndDate(LocalDate supportEndDate);
}


