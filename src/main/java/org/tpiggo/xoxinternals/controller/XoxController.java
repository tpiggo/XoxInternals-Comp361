package org.tpiggo.xoxinternals.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tpiggo.xoxinternals.model.XoxInitSettings;
import org.tpiggo.xoxinternals.service.XoxManager;

import java.util.ArrayList;
import java.util.List;

@RestController
public class XoxController {

    private final XoxManager xoxService;

    public XoxController(XoxManager aXoxService) {
        xoxService = aXoxService;
    }

    @GetMapping("xox")
    public List<Long> getGameIds(){
        return new ArrayList<>(xoxService.getGames());
    }

    @PostMapping("xox")
    public void addGame(@RequestBody XoxInitSettings gameSettings) {
        xoxService.addGame(gameSettings);
    }
}
