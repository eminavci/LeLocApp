package com.webproject.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.core.models.entities.Feature;
import com.webproject.core.models.entities.FeatureOptions;

public interface FeatureOptionsRepo extends JpaRepository<FeatureOptions, Long>{

	public List<FeatureOptions> findByFeature(Feature feature);
	
}
