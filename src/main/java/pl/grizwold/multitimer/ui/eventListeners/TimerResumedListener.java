package pl.grizwold.multitimer.ui.eventListeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.TimerPausedEvent;
import pl.grizwold.multitimer.events.TimerResumedEvent;

@Slf4j
@Component
public class TimerResumedListener {

    @EventListener
    public void execute(TimerResumedEvent timerResumedEvent) {
        log.info("Received event: {}", getClass().getSimpleName());
    }
}
