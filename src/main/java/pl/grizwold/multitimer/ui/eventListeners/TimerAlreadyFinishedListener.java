package pl.grizwold.multitimer.ui.eventListeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.TimerAlreadyFinishedEvent;
import pl.grizwold.multitimer.events.TimerFinishedEvent;

@Slf4j
@Component
public class TimerAlreadyFinishedListener {

    @EventListener
    public void execute(TimerAlreadyFinishedEvent timerAlreadyFinishedEvent) {
        log.info("Received event: {}", getClass().getSimpleName());
    }
}
