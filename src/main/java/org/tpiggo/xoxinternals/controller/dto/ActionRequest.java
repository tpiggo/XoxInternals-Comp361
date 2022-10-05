package org.tpiggo.xoxinternals.controller.dto;


public class ActionRequest {
    private final int actionId;
    public ActionRequest(int anActionId) {
        actionId = anActionId;
    }

    public int getActionId() {
        return actionId;
    }
}
