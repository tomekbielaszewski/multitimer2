package pl.grizwold.multitimer.logic.eventListeners;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.CancelTimerRequest;
import pl.grizwold.multitimer.events.Event;
import pl.grizwold.multitimer.events.TimerCancelledEvent;
import pl.grizwold.multitimer.events.TimerNotFoundEvent;
import pl.grizwold.multitimer.logic.TimerService;
import pl.grizwold.multitimer.logic.exception.TimerNotFoundException;

import java.util.Optional;

@Slf4j
@Component
public class CancelTimerRequestListener {
    private final TimerService timerService;

    @Autowired
    public CancelTimerRequestListener(TimerService timerService) {
        this.timerService = timerService;
    }

    @EventListener
    public Event execute(@NonNull CancelTimerRequest cancelTimer) {
        log.info("Received event: {}", getClass().getSimpleName());
        try {
            return Optional.of(cancelTimer)
                    .map(CancelTimerRequest::getId)
                    .map(this.timerService::cancelTimer)
                    .map(TimerCancelledEvent::new)
                    .get();
        } catch (TimerNotFoundException e) {
            return Optional.of(e)
                    .map(TimerNotFoundEvent::of)
                    .get();
        }
    }
}
