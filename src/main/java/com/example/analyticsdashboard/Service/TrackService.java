package com.example.analyticsdashboard.Service;

import com.example.analyticsdashboard.Entity.FeatureClick;
import com.example.analyticsdashboard.Repo.FeatureClickRepo;
import com.example.analyticsdashboard.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class TrackService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FeatureClickRepo featureClickRepo;

    public String trackFeature(FeatureClick featureClick) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        featureClick.setUser(userRepo.findByUsername(username).get());
        featureClick.setTimestamp(LocalDateTime.now());
        featureClickRepo.save(featureClick);
        return "Tracked Successfully";
    }
}
