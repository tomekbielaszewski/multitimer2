package pl.grizwold.multitimer.gui.event.handlers;

import pl.grizwold.multitimer.logic.model.LogicEvent;
import pl.grizwold.multitimer.logic.model.TimerFinished;

public class TimerFinishedHandler implements LogicEventHandler {
    @Override
    public boolean accepted(LogicEvent event) {
        return event instanceof TimerFinished;
    }

    @Override
    public void execute(LogicEvent event) {

    }
}
