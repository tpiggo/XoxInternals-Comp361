package org.tpiggo.xoxinternals.controller;

import org.springframework.web.bind.annotation.*;
import org.tpiggo.xoxinternals.controller.dto.ActionRequest;
import org.tpiggo.xoxinternals.model.Player;
import org.tpiggo.xoxinternals.service.XoxClaimFieldAction;
import org.tpiggo.xoxinternals.service.XoxManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("players")
public class PlayerController {
    private final XoxManager xoxService;

    public PlayerController(XoxManager aXoxService) {
        xoxService = aXoxService;
    }

    @GetMapping("/{id}")
    public List<Player> getPlayers(@PathVariable("id") Long id) {
        return Arrays.stream(xoxService.getPlayers(id)).collect(Collectors.toList());
    }

    @GetMapping("/{id}/{name}/actions")
    public XoxClaimFieldAction[] getPlayerActions(@PathVariable("id") Long id, @PathVariable("name") String name) {
        return xoxService.getActions(id, name);
    }

    @PostMapping("/{id}/{name}/{action}")
    public void movePlayer(@PathVariable("id") Long id, @PathVariable("name") String name,
                           @PathVariable("action") int action) {
        xoxService.performAction(id, name, action);
    }

    @PostMapping("/{id}/{name}/actions")
    public void movePlayerBetter(@PathVariable("id") Long id, @PathVariable("name") String name,
                           @RequestBody ActionRequest actionRequest) {
        xoxService.performAction(id, name, actionRequest.getActionId());
    }
}
