package com.example.controller;

import com.example.model.HealthMetric;
import com.example.service.HealthMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController  // Handles HTTP requests, returns JSON
@RequestMapping("/api/metrics")  // All endpoints start with /api/metrics
public class HealthMetricController {
    
    @Autowired  // Spring injects service (Dependency Injection)
    private HealthMetricService service;
    
    // GET /api/metrics - Get all metrics
    @GetMapping
    public ResponseEntity<List<HealthMetric>> getAllMetrics() {
        List<HealthMetric> metrics = service.getAllMetrics();
        return ResponseEntity.ok(metrics);  // 200 OK
    }
    
    // GET /api/metrics/{id} - Get specific metric
    @GetMapping("/{id}")
    public ResponseEntity<HealthMetric> getMetricById(@PathVariable Long id) {
        try {
            HealthMetric metric = service.getMetricById(id);
            return ResponseEntity.ok(metric);  // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }
    
    // GET /api/metrics/user/{userId} - Get metrics for specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HealthMetric>> getMetricsByUserId(@PathVariable String userId) {
        List<HealthMetric> metrics = service.getMetricsByUserId(userId);
        return ResponseEntity.ok(metrics);
    }
    
    // GET /api/metrics/user/{userId}/average - Get average heart rate for user
    @GetMapping("/user/{userId}/average")
    public ResponseEntity<Double> getAverageHeartRate(@PathVariable String userId) {
        Double average = service.getAverageHeartRate(userId);
        return ResponseEntity.ok(average);
    }
    
    // POST /api/metrics - Create new metric
    @PostMapping
    public ResponseEntity<HealthMetric> createMetric(@RequestBody HealthMetric metric) {
        try {
            HealthMetric savedMetric = service.saveMetric(metric);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMetric);  // 201 Created
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();  // 400 Bad Request
        }
    }
    
    // PUT /api/metrics/{id} - Update metric
    @PutMapping("/{id}")
    public ResponseEntity<HealthMetric> updateMetric(
            @PathVariable Long id,
            @RequestBody HealthMetric metric) {
        try {
            HealthMetric updatedMetric = service.updateMetric(id, metric);
            return ResponseEntity.ok(updatedMetric);  // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }
    
    // DELETE /api/metrics/{id} - Delete metric
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMetric(@PathVariable Long id) {
        try {
            service.deleteMetric(id);
            return ResponseEntity.noContent().build();  // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }
    
    // GET /api/metrics/high-heart-rate/{threshold} - Get high heart rate metrics
    @GetMapping("/high-heart-rate/{threshold}")
    public ResponseEntity<List<HealthMetric>> getHighHeartRateMetrics(@PathVariable Integer threshold) {
        List<HealthMetric> metrics = service.getHighHeartRateMetrics(threshold);
        return ResponseEntity.ok(metrics);
    }
}