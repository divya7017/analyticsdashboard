package com.example.analyticsdashboard.Service;

import com.example.analyticsdashboard.Entity.FeatureClick;
import com.example.analyticsdashboard.Entity.User;
import com.example.analyticsdashboard.Repo.FeatureClickRepo;
import com.example.analyticsdashboard.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class DummyDataService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FeatureClickRepo featureClickRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String generateDummyUsers() {

        List<Long> validUserIds = new ArrayList<>();

        // add 1 and 2
        validUserIds.add(1L);
        validUserIds.add(2L);

        // add 82 to 652
        for (long i = 82; i <= 652; i++) {
            validUserIds.add(i);
        }

        String[] features = {
                "filter_gender",
                "filter_age",
                "reset_filters",
                "chart_line_click",
                "date_picker"
        };

        Random random = new Random();
        List<FeatureClick> batch = new ArrayList<>();

        LocalDateTime start = LocalDateTime.of(2026, 1, 20, 0, 0);
        LocalDateTime end = LocalDateTime.of(2026, 2, 21, 23, 59);

        long startEpoch = start.atZone(java.time.ZoneId.systemDefault()).toEpochSecond();
        long endEpoch = end.atZone(java.time.ZoneId.systemDefault()).toEpochSecond();

        for (int i = 0; i < 10000; i++) {

            long randomEpoch = startEpoch +
                    (long) (random.nextDouble() * (endEpoch - startEpoch));

            LocalDateTime randomTime = LocalDateTime.ofEpochSecond(
                    randomEpoch,
                    0,
                    java.time.ZoneOffset.systemDefault().getRules().getOffset(start)
            );

            Long randomUserId = validUserIds.get(random.nextInt(validUserIds.size()));
            Optional<User> userOpt = userRepo.findById(randomUserId);

            if (userOpt.isEmpty()) continue;  // skip if user not found

            FeatureClick click = new FeatureClick();
            click.setFeatureName(features[random.nextInt(features.length)]);
            click.setTimestamp(randomTime);
            click.setUser(userOpt.get());

            batch.add(click);

            if (batch.size() == 200) {
                featureClickRepo.saveAll(batch);
                batch.clear();
            }
        }

        if (!batch.isEmpty()) {
            featureClickRepo.saveAll(batch);
        }
        return "Success";
    }
}
