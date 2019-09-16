package pl.grizwold.multitimer.logic.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class Timer {
    private UUID id;
    private String name;
    private LocalDateTime creation;
    private LocalDateTime finish;
}
