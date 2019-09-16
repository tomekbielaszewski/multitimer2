package pl.grizwold.multitimer.logic.eventListeners;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.*;
import pl.grizwold.multitimer.logic.TimerService;
import pl.grizwold.multitimer.logic.model.Timer;
import pl.grizwold.multitimer.logic.exception.TimerNotFoundException;

import java.util.Optional;

@Component
public class RenameTimerRequestListener {
    private final TimerService timerService;

    @Autowired
    public RenameTimerRequestListener(TimerService timerService) {
        this.timerService = timerService;
    }

    @EventListener
    public Event execute(@NonNull RenameTimerRequest renameTimerRequest) {
        try {
            return Optional.of(renameTimerRequest)
                    .map(request -> this.timerService.rename(request.getName(), request.getId()))
                    .map(this::toTimerNameChangedEvent)
                    .get();
        } catch (TimerNotFoundException e) {
            return Optional.of(e)
                    .map(TimerNotFoundEvent::of)
                    .get();
        }
    }

    private TimerNameChangedEvent toTimerNameChangedEvent(Timer timer) {
        return TimerNameChangedEvent.builder()
                .id(timer.getId())
                .name(timer.getName())
                .build();
    }
}
