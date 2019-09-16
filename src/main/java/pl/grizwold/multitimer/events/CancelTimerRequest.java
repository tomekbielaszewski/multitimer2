package pl.grizwold.multitimer.events;

import lombok.Value;

import java.util.EventListener;
import java.util.UUID;

@Value
public class CancelTimerRequest implements Event {
    private UUID id;
}
