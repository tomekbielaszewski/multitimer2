package pl.grizwold.multitimer.logic.exception;

import lombok.Value;

import java.util.UUID;

@Value
public class TimerNotFoundException extends RuntimeException {
    private UUID id;
}
