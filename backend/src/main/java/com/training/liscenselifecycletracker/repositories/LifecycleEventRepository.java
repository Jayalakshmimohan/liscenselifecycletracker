package com.training.liscenselifecycletracker.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.liscenselifecycletracker.entities.LifecycleEvent;

@Repository
public interface LifecycleEventRepository extends CrudRepository<LifecycleEvent, Long> {

	Optional<LifecycleEvent> findByAssetIdAndEventType(Long assetId, String eventType);
	LifecycleEvent findByAssetId(Long assetId);
    // Custom queries can be added here if needed
}
