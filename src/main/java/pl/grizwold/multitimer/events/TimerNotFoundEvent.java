package pl.grizwold.multitimer.events;

import lombok.Getter;
import lombok.NonNull;
import pl.grizwold.multitimer.logic.exception.TimerNotFoundException;

import java.util.UUID;

@Getter
public class TimerNotFoundEvent implements Event {
    private UUID id;

    private TimerNotFoundEvent(@NonNull UUID id) {
        this.id = id;
    }

    public static TimerNotFoundEvent of(@NonNull TimerNotFoundException e) {
        return new TimerNotFoundEvent(e.getId());
    }
}
