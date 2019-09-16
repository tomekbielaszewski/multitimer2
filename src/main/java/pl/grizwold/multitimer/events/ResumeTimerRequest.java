package pl.grizwold.multitimer.events;

import lombok.Value;

import java.util.UUID;

@Value
public class ResumeTimerRequest {
    private UUID id;
}
