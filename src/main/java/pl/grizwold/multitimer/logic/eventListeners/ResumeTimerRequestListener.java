package pl.grizwold.multitimer.logic.eventListeners;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.*;
import pl.grizwold.multitimer.logic.TimerService;
import pl.grizwold.multitimer.logic.exception.TimerAlreadyFinishedException;
import pl.grizwold.multitimer.logic.exception.TimerNotFoundException;
import pl.grizwold.multitimer.logic.model.Timer;

import java.util.Optional;

@Component
public class ResumeTimerRequestListener {
    private final TimerService timerService;

    @Autowired
    public ResumeTimerRequestListener(TimerService timerService) {
        this.timerService = timerService;
    }

    @EventListener
    public Event execute(@NonNull ResumeTimerRequest resumeTimerRequest) {
        try {
            return Optional.of(resumeTimerRequest)
                    .map(ResumeTimerRequest::getId)
                    .map(this.timerService::resume)
                    .map(this::toTimerResumedEvent)
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

    private TimerResumedEvent toTimerResumedEvent(Timer timer) {
        return TimerResumedEvent.builder()
                .id(timer.getId())
                .newFinishDate(timer.getFinish())
                .build();
    }
}
