package pl.grizwold.multitimer.logic;

import org.springframework.stereotype.Service;
import pl.grizwold.multitimer.logic.model.Timer;

import java.util.List;
import java.util.UUID;

@Service
public class TimerDao {
    List<Timer> getExpired() {
        return null;
    }

    UUID save(Timer timer) {
        return null;
    }

    void markAsFinished(UUID id) {

    }

    void stop(UUID id) {

    }

    public Timer get(UUID id) {
        return null;
    }

    public void markAsPaused(boolean b, UUID id) {

    }
}
