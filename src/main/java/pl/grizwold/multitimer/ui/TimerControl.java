package pl.grizwold.multitimer.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.*;

import java.util.UUID;

@Component
public class TimerControl {
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public TimerControl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void newTimer(String name, long durationSeconds) {
        NewTimerRequest newTimerRequest = new NewTimerRequest(UUID.randomUUID(), name, durationSeconds);
        eventPublisher.publishEvent(newTimerRequest);
    }

    public void cancelTimer(UUID id) {
        CancelTimerRequest cancelTimerRequest = new CancelTimerRequest(id);
        eventPublisher.publishEvent(cancelTimerRequest);
    }

    public void extendTimer(UUID id, long durationAdded) {
        ExtendTimerRequest extendTimerRequest = new ExtendTimerRequest(id, durationAdded);
        eventPublisher.publishEvent(extendTimerRequest);
    }

    public void renameTimer(UUID id, String name) {
        RenameTimerRequest renameTimerRequest = new RenameTimerRequest(id, name);
        eventPublisher.publishEvent(renameTimerRequest);
    }

    public void shortenTimer(UUID id, long durationSubtracted) {
        ShortenTimerRequest shortenTimerRequest = new ShortenTimerRequest(id, durationSubtracted);
        eventPublisher.publishEvent(shortenTimerRequest);
    }

    public void pauseTimer(UUID id) {
        PauseTimerRequest pauseTimerRequest = new PauseTimerRequest(id);
        eventPublisher.publishEvent(pauseTimerRequest);
    }

    public void resumeTimer(UUID id) {
        ResumeTimerRequest resumeTimerRequest = new ResumeTimerRequest(id);
        eventPublisher.publishEvent(resumeTimerRequest);
    }
}
