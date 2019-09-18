package pl.grizwold.multitimer.ui.eventListeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.TimerCancelledEvent;
import pl.grizwold.multitimer.events.TimerFinishDateChangedEvent;

@Slf4j
@Component
public class TimerFinishDateChangedListener {

    @EventListener
    public void execute(TimerFinishDateChangedEvent timerFinishDateChangedEvent) {
        log.info("Received event: {}", getClass().getSimpleName());
    }
}
