package pl.grizwold.multitimer.logic.model;

import lombok.Value;

import java.util.UUID;

@Value
public class TimerNotFoundException extends RuntimeException {
    private UUID id;
}
