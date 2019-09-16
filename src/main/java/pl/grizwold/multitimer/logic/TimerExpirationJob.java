package pl.grizwold.multitimer.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import pl.grizwold.multitimer.events.TimerFinishedEvent;
import pl.grizwold.multitimer.logic.model.Timer;

public class TimerExpirationJob {
    private static final long UPDATE_RATE = 200;

    private final TimerService timerService;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public TimerExpirationJob(TimerService timerService, ApplicationEventPublisher eventPublisher) {
        this.timerService = timerService;
        this.eventPublisher = eventPublisher;
    }

    @Scheduled(fixedRate = UPDATE_RATE)
    public void update() {
        timerService.getExpiredTimers().stream()
                .map(this::toTimerFinishedEvent)
                .forEach(this.eventPublisher::publishEvent);
    }

    private TimerFinishedEvent toTimerFinishedEvent(Timer timer) {
        return TimerFinishedEvent.builder()
                .id(timer.getId())
                .build();
    }
}
