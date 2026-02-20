package com.example.analyticsdashboard.Controller;

import com.example.analyticsdashboard.Entity.FeatureClick;
import com.example.analyticsdashboard.Repo.FeatureClickRepo;
import com.example.analyticsdashboard.Service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Track;

@RestController
@RequestMapping("/track")
public class TrackController {

    @Autowired
    private TrackService trackService;
    @Autowired
    private FeatureClickRepo featureClickRepo;

    @PostMapping
    public String track(@RequestBody FeatureClick featureClick) {
        return trackService.trackFeature(featureClick);
    }


}
