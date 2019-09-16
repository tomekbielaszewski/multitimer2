package pl.grizwold.multitimer.events;

import lombok.Value;

import java.util.UUID;

@Value
public class TimerNotFound implements Event {
    private UUID id;
}
