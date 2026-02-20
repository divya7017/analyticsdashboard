package com.example.analyticsdashboard.Service;

import com.example.analyticsdashboard.Repo.FeatureClickRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {

    @Autowired
    private FeatureClickRepo featureClickRepo;

    public List<Map<String, Object>> getBar(String gender, Integer minAge, Integer maxAge, LocalDateTime startDate, LocalDateTime endDate) {
        List<Object[]> results = featureClickRepo.getFilteredBarData(
                gender, minAge, maxAge, startDate, endDate
        );

        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> data = new HashMap<>();
            data.put("feature", row[0]);
            data.put("count", row[1]);
            response.add(data);
        }
        return response;
    }


    public List<Map<String, Object>> getLineChartData(
            LocalDateTime startDate,
            LocalDateTime endDate,
            String gender,
            Integer minAge,
            Integer maxAge
    ) {

        List<Object[]> results =
                featureClickRepo.getLineChartData(
                        startDate, endDate, gender, minAge, maxAge
                );

        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> data = new HashMap<>();
            data.put("date", row[0]);
            data.put("count", row[1]);
            response.add(data);
        }

        return response;
    }
}
