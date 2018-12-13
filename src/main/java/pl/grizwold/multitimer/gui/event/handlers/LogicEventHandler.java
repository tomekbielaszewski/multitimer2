package pl.grizwold.multitimer.gui.event.handlers;

import pl.grizwold.multitimer.logic.model.LogicEvent;

public interface LogicEventHandler {
    boolean accepted(LogicEvent event);
    void execute(LogicEvent event);
}
