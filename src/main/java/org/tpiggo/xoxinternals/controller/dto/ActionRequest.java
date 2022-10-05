package org.tpiggo.xoxinternals.controller.dto;

public class ActionRequest {
    private int actionId;

    public ActionRequest() {
        actionId = -1;
    }

    public ActionRequest(int anActionId) {
        actionId = anActionId;
    }

    public void setActionId(int anActionId) {
        if (anActionId < 0) {
            throw new RuntimeException("ActionId must be greater than one");
        }
        actionId = anActionId;
    }

    public int getActionId() {
        if (actionId < 0) {
            throw new RuntimeException("ActionId must be greater than one");
        }
        return actionId;
    }
}
