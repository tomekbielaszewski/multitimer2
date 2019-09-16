package pl.grizwold.multitimer.logic.eventListeners;

import pl.grizwold.multitimer.events.Event;
import pl.grizwold.multitimer.events.TimerMarkedAsFinished;
import pl.grizwold.multitimer.logic.TimerService;

public class TimerMarkedAsFinishedListener {
    private final TimerService timerService;

    public TimerMarkedAsFinishedListener(TimerService timerService) {
        this.timerService = timerService;
    }

    public void execute(TimerMarkedAsFinished timerMarkedAsFinished) {
        this.timerService.finishTimer(timerMarkedAsFinished.getId());
    }
}
