package pl.grizwold.multitimer.logic.eventListeners;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.*;
import pl.grizwold.multitimer.logic.TimerService;
import pl.grizwold.multitimer.logic.exception.TimerAlreadyFinishedException;
import pl.grizwold.multitimer.logic.exception.TimerNotFoundException;

import java.util.Optional;

@Component
public class PauseTimerRequestListener {
    private final TimerService timerService;

    @Autowired
    public PauseTimerRequestListener(TimerService timerService) {
        this.timerService = timerService;
    }

    @EventListener
    public Event execute(@NonNull PauseTimerRequest pauseTimerRequest) {
        try {
            return Optional.of(pauseTimerRequest)
                    .map(PauseTimerRequest::getId)
                    .map(this.timerService::pause)
                    .map(TimerPausedEvent::new)
                    .get();
        } catch (TimerNotFoundException e) {
            return Optional.of(e)
                    .map(TimerNotFoundEvent::of)
                    .get();
        } catch (TimerAlreadyFinishedException e) {
            return Optional.of(e)
                    .map(TimerAlreadyFinishedEvent::of)
                    .get();
        }
    }
}
