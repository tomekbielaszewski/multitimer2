package pl.grizwold.multitimer.events;

import lombok.Builder;

import java.util.UUID;

@Builder
public class TimerFinishedEvent implements Event {
    private UUID id;
}
