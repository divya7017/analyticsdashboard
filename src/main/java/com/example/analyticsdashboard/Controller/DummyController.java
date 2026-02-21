package com.example.analyticsdashboard.Controller;

import com.example.analyticsdashboard.Service.DummyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/dummy")
public class DummyController {

    @Autowired
    private DummyDataService dummyDataService;

    @GetMapping
    public String dummyUsers() {
        return dummyDataService.generateDummyUsers();
    }
}
