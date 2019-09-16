package pl.grizwold.multitimer.logic.eventListeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.Event;
import pl.grizwold.multitimer.events.TimerMarkedAsFinished;
import pl.grizwold.multitimer.logic.TimerService;

@Component
public class TimerMarkedAsFinishedListener {
    private final TimerService timerService;

    @Autowired
    public TimerMarkedAsFinishedListener(TimerService timerService) {
        this.timerService = timerService;
    }

    @EventListener
    public void execute(TimerMarkedAsFinished timerMarkedAsFinished) {
        this.timerService.finishTimer(timerMarkedAsFinished.getId());
    }
}
