package pl.grizwold.multitimer.ui.eventListeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.TimerFinishedEvent;
import pl.grizwold.multitimer.events.TimerNotFoundEvent;

@Slf4j
@Component
public class TimerNotFoundListener {

    @EventListener
    public void execute(TimerNotFoundEvent timerNotFoundEvent) {
        log.info("Received event: {}", getClass().getSimpleName());
    }
}
