package pl.grizwold.multitimer.gui.model;

public class TimerCreated {
    private Long timerId;
    private String name;
    private long finishTimestamp;

    public Long getTimerId() {
        return timerId;
    }

    public void setTimerId(Long timerId) {
        this.timerId = timerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFinishTimestamp() {

        return finishTimestamp;
    }

    public void setFinishTimestamp(long finishTimestamp) {
        this.finishTimestamp = finishTimestamp;
    }
}
