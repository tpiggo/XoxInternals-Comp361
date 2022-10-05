package org.tpiggo.xoxinternals.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tpiggo.xoxinternals.model.BoardReadOnly;
import org.tpiggo.xoxinternals.service.XoxManager;

@RestController
@RequestMapping("board")
public class BoardController {
    private final XoxManager xoxService;

    public BoardController(XoxManager aXoxService) {
        xoxService = aXoxService;
    }

    @GetMapping("/{id}")
    public BoardReadOnly getBoard(@PathVariable("id") Long id) {
        return xoxService.getBoard(id);
    }
}
