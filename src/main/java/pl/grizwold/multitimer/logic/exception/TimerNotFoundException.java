package pl.grizwold.multitimer.logic.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = false)
public class TimerNotFoundException extends RuntimeException {
    private UUID id;
}
