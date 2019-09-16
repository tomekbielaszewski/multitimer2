package pl.grizwold.multitimer.events;

import lombok.Value;

import java.util.UUID;

@Value
public class TimerCancelledEvent implements Event {
    private UUID id;
}
