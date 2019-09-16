package pl.grizwold.multitimer.logic.eventListeners;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.Event;
import pl.grizwold.multitimer.events.ExtendTimerRequest;
import pl.grizwold.multitimer.events.TimerFinishDateChangedEvent;
import pl.grizwold.multitimer.events.TimerNotFoundEvent;
import pl.grizwold.multitimer.logic.TimerService;
import pl.grizwold.multitimer.logic.model.Timer;
import pl.grizwold.multitimer.logic.model.TimerNotFoundException;

import java.util.Optional;

@Component
public class ExtendTimerRequestListener {
    private final TimerService timerService;

    @Autowired
    public ExtendTimerRequestListener(TimerService timerService) {
        this.timerService = timerService;
    }

    @EventListener
    public Event execute(@NonNull ExtendTimerRequest extendTimerRequest) {
        try {
            return Optional.of(extendTimerRequest)
                    .map(request -> this.timerService.extend(request.getDurationAdded(), request.getId()))
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
