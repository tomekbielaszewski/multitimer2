package pl.grizwold.multitimer.logic;

import org.springframework.stereotype.Service;
import pl.grizwold.multitimer.logic.exception.TimerNotFoundException;
import pl.grizwold.multitimer.logic.model.Timer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TimerDao {
    private List<Timer> timers = new ArrayList<>();

    List<Timer> getExpired() {
        LocalDateTime now = LocalDateTime.now();
        return timers.stream()
                .filter(t -> now.isAfter(t.getFinish()))
                .filter(t -> !t.isFinished())
                .filter(t -> !t.isPaused())
                .collect(Collectors.toList());
    }

    UUID save(Timer timer) {
        timers.add(timer);
        return timer.getId();
    }

    void markAsFinished(UUID id) {
        get(id).setFinished(true);
    }

    void stop(UUID id) {
        get(id).setFinish(LocalDateTime.now());
    }

    public void markAsPaused(boolean paused, UUID id) {
        get(id).setPaused(paused);
    }

    public Timer get(UUID id) {
        return timers.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TimerNotFoundException(id));
    }
}
