package pl.grizwold.multitimer.logic.eventListeners;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.TimerMarkedAsFinished;
import pl.grizwold.multitimer.logic.TimerService;

@Slf4j
@Component
public class TimerMarkedAsFinishedListener {
    private final TimerService timerService;

    @Autowired
    public TimerMarkedAsFinishedListener(TimerService timerService) {
        this.timerService = timerService;
    }

    @EventListener
    public void execute(@NonNull TimerMarkedAsFinished timerMarkedAsFinished) {
        log.info("Received event: {}", getClass().getSimpleName());
        this.timerService.finishTimer(timerMarkedAsFinished.getId());
    }
}
