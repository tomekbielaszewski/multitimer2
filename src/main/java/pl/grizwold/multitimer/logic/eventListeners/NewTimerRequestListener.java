package pl.grizwold.multitimer.logic.eventListeners;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.Event;
import pl.grizwold.multitimer.events.NewTimerRequest;
import pl.grizwold.multitimer.events.TimerStartedEvent;
import pl.grizwold.multitimer.logic.TimerService;
import pl.grizwold.multitimer.logic.model.Timer;

import java.util.Optional;

@Slf4j
@Component
public class NewTimerRequestListener {
    private final TimerService timerService;

    @Autowired
    public NewTimerRequestListener(TimerService timerService) {
        this.timerService = timerService;
    }

    @EventListener
    public Event execute(@NonNull NewTimerRequest newTimerRequest) {
        log.info("Received event: {}", getClass().getSimpleName());
        return Optional.of(newTimerRequest)
                .map(this::createTimer)
                .map(this::toTimerStartedEvent)
                .get();
    }

    private TimerStartedEvent toTimerStartedEvent(Timer timer) {
        return TimerStartedEvent.builder()
                .id(timer.getId())
                .finish(timer.getFinish())
                .build();
    }

    private Timer createTimer(NewTimerRequest newTimerRequest) {
        return timerService.createTimer(
                newTimerRequest.getId(),
                newTimerRequest.getName(),
                newTimerRequest.getDuration()
        );
    }
}
