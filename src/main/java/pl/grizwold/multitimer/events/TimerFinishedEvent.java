package pl.grizwold.multitimer.events;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class TimerFinishedEvent implements Event {
    private UUID id;
}
