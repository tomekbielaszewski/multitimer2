package pl.grizwold.multitimer.logic.eventListeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.Event;
import pl.grizwold.multitimer.events.NewTimerRequest;
import pl.grizwold.multitimer.events.TimerStartedEvent;
import pl.grizwold.multitimer.logic.TimerService;
import pl.grizwold.multitimer.logic.model.Timer;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Component
public class NewTimerRequestListener {
    private final TimerService timerService;

    @Autowired
    public NewTimerRequestListener(TimerService timerService) {
        this.timerService = timerService;
    }

    @EventListener
    public Event execute(NewTimerRequest newTimerRequest) {
        return Optional.of(newTimerRequest)
                .map(this::toTimer)
                .map(this.timerService::createTimer)
                .map(this::toTimerStartedEvent)
                .get();
    }

    private TimerStartedEvent toTimerStartedEvent(UUID id) {
        return TimerStartedEvent.builder()
                .id(id)
                .build();
    }

    private Timer toTimer(NewTimerRequest newTimerRequest) {
        LocalDateTime now = LocalDateTime.now();
        return Timer.builder()
                .id(newTimerRequest.getId())
                .name(newTimerRequest.getName())
                .creation(now)
                .finish(now.plus(newTimerRequest.getDuration(), ChronoUnit.SECONDS))
                .build();
    }
}
