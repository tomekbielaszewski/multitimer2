package pl.grizwold.multitimer.logic.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Timer {
    private Long id;
    private String name;
    private long timestamp;
    private boolean finished;

    public LogicEvent update() {
        return null;
    }

    public boolean isRunning() {
        return !this.isFinished();
    }
}
