package com.example.analyticsdashboard.Controller;

import com.example.analyticsdashboard.Service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/bar")
    public List<Map<String, Object>> bar(
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endDate
    ) {

        if (endDate != null) {
            endDate = endDate.plusDays(1);
        }

        return analyticsService.getBar(
                gender,
                minAge,
                maxAge,
                startDate,
                endDate
        );
    }
    @GetMapping("/lineChart")
    public List<Map<String, Object>> lineChart(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endDate,

            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge
    ) {
        return analyticsService.getLineChartData(
                startDate, endDate, gender, minAge, maxAge
        );
    }
}

