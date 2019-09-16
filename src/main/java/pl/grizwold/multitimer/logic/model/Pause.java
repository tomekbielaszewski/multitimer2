package pl.grizwold.multitimer.logic.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Pause {
    private UUID id;
    private long durationLeft;
}
