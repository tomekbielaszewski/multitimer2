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

    void stop(UUID uuid) {

    }
}
