package pl.grizwold.multitimer.logic;

import org.springframework.stereotype.Service;
import pl.grizwold.multitimer.logic.model.Timer;

import java.util.List;
import java.util.UUID;

@Service
public class TimerDao {
    public List<Timer> getExpired() {
        return null;
    }

    public UUID save(Timer timer) {
        return null;
    }

    public void markAsFinished(UUID id) {

    }
}
