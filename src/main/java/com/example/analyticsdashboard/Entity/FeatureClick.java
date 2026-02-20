package com.example.analyticsdashboard.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "feature_clicks")
public class FeatureClick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String featureName;
    private LocalDateTime timestamp;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
