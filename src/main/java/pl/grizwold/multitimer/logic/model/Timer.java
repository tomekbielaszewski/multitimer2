package pl.grizwold.multitimer.logic.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Timer {
    private UUID id;
    private String name;
    private LocalDateTime creation;
    private LocalDateTime finish;
    private boolean finished;
    private boolean paused;
}
