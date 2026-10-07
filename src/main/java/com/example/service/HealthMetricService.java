package com.example.service;

import com.example.model.HealthMetric;
import com.example.repository.HealthMetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service  // This is the service layer (business logic)
public class HealthMetricService {
    
    @Autowired  // Spring injects repository automatically (Dependency Injection)
    private HealthMetricRepository repository;
    
    // Create/Save a metric
    public HealthMetric saveMetric(HealthMetric metric) {
        // Business logic: validate before saving
        if (metric.getHeartRate() < 0 || metric.getHeartRate() > 300) {
            throw new IllegalArgumentException("Invalid heart rate");
        }
        
        if (metric.getSystolicBP() < 0 || metric.getSystolicBP() > 300) {
            throw new IllegalArgumentException("Invalid systolic BP");
        }
        
        // Save to database
        return repository.save(metric);
    }
    
    // Get metric by ID
    public HealthMetric getMetricById(Long id) {
        Optional<HealthMetric> metric = repository.findById(id);
        
        // If not found, throw exception (we'll handle in controller)
        if (!metric.isPresent()) {
            throw new RuntimeException("Metric not found with id: " + id);
        }
        
        return metric.get();
    }
    
    // Get all metrics
    public List<HealthMetric> getAllMetrics() {
        return repository.findAll();
    }
    
    // Get metrics for specific user
    public List<HealthMetric> getMetricsByUserId(String userId) {
        return repository.findByUserId(userId);
    }
    
    // Get high heart rate metrics (business logic example)
    public List<HealthMetric> getHighHeartRateMetrics(Integer threshold) {
        return repository.findByHeartRateGreaterThan(threshold);
    }
    
    // Update metric
    public HealthMetric updateMetric(Long id, HealthMetric updatedMetric) {
        HealthMetric existing = getMetricById(id);
        
        existing.setHeartRate(updatedMetric.getHeartRate());
        existing.setSystolicBP(updatedMetric.getSystolicBP());
        existing.setDiastolicBP(updatedMetric.getDiastolicBP());
        
        return repository.save(existing);
    }
    
    // Delete metric
    public void deleteMetric(Long id) {
        repository.deleteById(id);
    }
    
    // Business logic: Calculate average heart rate
    public Double getAverageHeartRate(String userId) {
        List<HealthMetric> metrics = getMetricsByUserId(userId);
        
        if (metrics.isEmpty()) {
            return 0.0;
        }
        
        Integer sum = metrics.stream()
            .mapToInt(HealthMetric::getHeartRate)
            .sum();
        
        return (double) sum / metrics.size();
    }
}