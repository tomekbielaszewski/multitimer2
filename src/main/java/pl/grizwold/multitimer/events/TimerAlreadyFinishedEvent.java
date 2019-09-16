package pl.grizwold.multitimer.events;

import lombok.Getter;
import lombok.NonNull;
import pl.grizwold.multitimer.logic.exception.TimerAlreadyFinishedException;

import java.util.UUID;

@Getter
public class TimerAlreadyFinishedEvent implements Event {
    private UUID id;

    private TimerAlreadyFinishedEvent(@NonNull UUID id) {
        this.id = id;
    }

    public static TimerAlreadyFinishedEvent of(@NonNull TimerAlreadyFinishedException e) {
        return new TimerAlreadyFinishedEvent(e.getId());
    }
}
