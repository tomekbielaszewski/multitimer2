package pl.grizwold.multitimer.events;

import lombok.Value;

import java.util.UUID;

@Value
public class PauseTimerRequest {
    private UUID id;
}
