package org.tpiggo.xoxinternals.controller;

import org.springframework.web.bind.annotation.*;
import org.tpiggo.xoxinternals.model.XoxInitSettings;
import org.tpiggo.xoxinternals.service.Ranking;
import org.tpiggo.xoxinternals.service.XoxManager;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("xox")
public class XoxController {

    private final XoxManager xoxService;

    public XoxController(XoxManager aXoxService) {
        xoxService = aXoxService;
    }

    @GetMapping("/")
    public List<Long> getGameIds(){
        return new ArrayList<>(xoxService.getGames());
    }

    @PostMapping("/")
    public void addGame(@RequestBody XoxInitSettings gameSettings) {
        xoxService.addGame(gameSettings);
    }

    @GetMapping("/{id}")
    public Ranking getGame(@PathVariable("id") Long id) {
        return xoxService.getRanking(id);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable("id") Long id) {
        xoxService.removeGame(id);
    }

}
