package pl.grizwold.multitimer.events;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class NewTimerRequest implements Event {
    private UUID id;
    private String name;
    private long duration;
}
