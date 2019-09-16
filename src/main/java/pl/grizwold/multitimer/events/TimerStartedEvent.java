package pl.grizwold.multitimer.events;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class TimerStartedEvent implements Event {
    private UUID id;
}
