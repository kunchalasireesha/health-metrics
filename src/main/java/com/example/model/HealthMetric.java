package com.example.model;

import jakarta.persistence.*;

@Entity  // This is a database table
@Table(name = "health_metrics")
public class HealthMetric {
    
    @Id  // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String userId;
    
    @Column(nullable = false)
    private Integer heartRate;
    
    @Column(nullable = false)
    private Integer systolicBP;  // Systolic blood pressure
    
    @Column(nullable = false)
    private Integer diastolicBP;  // Diastolic blood pressure
    
    @Column(nullable = false)
    private String timestamp;
    
    // Empty constructor (JPA requires this)
    public HealthMetric() {}
    
    // Constructor with parameters
    public HealthMetric(String userId, Integer heartRate, Integer systolicBP, Integer diastolicBP) {
        this.userId = userId;
        this.heartRate = heartRate;
        this.systolicBP = systolicBP;
        this.diastolicBP = diastolicBP;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public Integer getHeartRate() {
        return heartRate;
    }
    
    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }
    
    public Integer getSystolicBP() {
        return systolicBP;
    }
    
    public void setSystolicBP(Integer systolicBP) {
        this.systolicBP = systolicBP;
    }
    
    public Integer getDiastolicBP() {
        return diastolicBP;
    }
    
    public void setDiastolicBP(Integer diastolicBP) {
        this.diastolicBP = diastolicBP;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
