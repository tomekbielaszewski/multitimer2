package pl.grizwold.multitimer.ui.eventListeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.TimerResumedEvent;
import pl.grizwold.multitimer.events.TimerStartedEvent;

@Slf4j
@Component
public class TimerStartedListener {

    @EventListener
    public void execute(TimerStartedEvent timerStartedEvent) {
        log.info("Received event: {}", getClass().getSimpleName());
    }
}
