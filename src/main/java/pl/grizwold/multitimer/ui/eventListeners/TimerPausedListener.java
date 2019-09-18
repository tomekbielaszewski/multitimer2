package pl.grizwold.multitimer.ui.eventListeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.TimerFinishedEvent;
import pl.grizwold.multitimer.events.TimerPausedEvent;

@Slf4j
@Component
public class TimerPausedListener {

    @EventListener
    public void execute(TimerPausedEvent timerPausedEvent) {
        log.info("Received event: {}", getClass().getSimpleName());
    }
}
