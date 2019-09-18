package pl.grizwold.multitimer.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.events.NewTimerRequest;

import java.util.UUID;

@Component
public class TimerControl {
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public TimerControl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void newTimer() {
        eventPublisher.publishEvent(NewTimerRequest.builder().duration(5).id(UUID.randomUUID()).build());
    }

    public void cancelTimer() {
    }

    public void extendTimer() {
    }

    public void renameTimer() {
    }

    public void shortenTimer() {
    }

    public void pauseTimer() {
    }

    public void resumeTimer() {
    }

    public void timerFinishedCallback() {
    }
}
