package pl.grizwold.multitimer.events;

import lombok.Value;

import java.util.UUID;

@Value
public class RenameTimerRequest implements Event {
    private UUID id;
    private String name;
}
