package pl.grizwold.multitimer.events;

import lombok.Value;

import java.util.UUID;

@Value
public class ShortenTimerRequest implements Event {
    private UUID id;
    private long durationSubtracted;
}
