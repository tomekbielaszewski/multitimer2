package pl.grizwold.multitimer.logic.eventListeners;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.*;
import pl.grizwold.multitimer.logic.TimerService;
import pl.grizwold.multitimer.logic.model.Timer;
import pl.grizwold.multitimer.logic.exception.TimerNotFoundException;

import java.util.Optional;

@Slf4j
@Component
public class ShortenTimerRequestListener {
    private final TimerService timerService;

    @Autowired
    public ShortenTimerRequestListener(TimerService timerService) {
        this.timerService = timerService;
    }

    @EventListener
    public Event execute(@NonNull ShortenTimerRequest shortenTimerRequest) {
        log.info("Received event: {}", getClass().getSimpleName());
        try {
            return Optional.of(shortenTimerRequest)
                    .map(request -> this.timerService.shorten(request.getDurationSubtracted(), request.getId()))
                    .map(this::toTimerFinishDateChangedEvent)
                    .get();
        } catch (TimerNotFoundException e) {
            return Optional.of(e)
                    .map(TimerNotFoundEvent::of)
                    .get();
        }
    }

    private TimerFinishDateChangedEvent toTimerFinishDateChangedEvent(Timer timer) {
        return TimerFinishDateChangedEvent.builder()
                .id(timer.getId())
                .newFinishDate(timer.getFinish())
                .build();
    }
}
