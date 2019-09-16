package pl.grizwold.multitimer.events;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class TimerNameChangedEvent implements Event {
    private UUID id;
    private String name;
}
