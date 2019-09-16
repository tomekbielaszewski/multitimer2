package pl.grizwold.multitimer.events;

import lombok.Value;

import java.util.UUID;

@Value
public class ExtendTimerRequest implements Event {
    private UUID id;
    private long durationAdded;
}
