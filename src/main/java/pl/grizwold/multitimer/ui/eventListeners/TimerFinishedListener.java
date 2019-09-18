package pl.grizwold.multitimer.ui.eventListeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.TimerFinishedEvent;
import pl.grizwold.multitimer.events.TimerMarkedAsFinished;

@Slf4j
@Component
public class TimerFinishedListener {

    @EventListener
    public TimerMarkedAsFinished execute(TimerFinishedEvent timerFinishedEvent) {
        log.info("Received event: {}", getClass().getSimpleName());
        return new TimerMarkedAsFinished(timerFinishedEvent.getId());
    }
}
