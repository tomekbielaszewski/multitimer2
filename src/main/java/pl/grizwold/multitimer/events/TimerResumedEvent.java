package pl.grizwold.multitimer.events;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class TimerResumedEvent implements Event {
    private UUID id;
    private LocalDateTime newFinishDate;
}
