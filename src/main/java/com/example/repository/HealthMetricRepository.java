package com.example.repository;

import com.example.model.HealthMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HealthMetricRepository extends JpaRepository<HealthMetric, Long> {
    
    // Spring Data JPA provides basic methods automatically:
    // - findById(Long id) - find by primary key
    // - findAll() - get all records
    // - save(HealthMetric metric) - create or update
    // - deleteById(Long id) - delete by id
    
    // Custom query methods
    List<HealthMetric> findByUserId(String userId);
    
    List<HealthMetric> findByHeartRateGreaterThan(Integer heartRate);
}