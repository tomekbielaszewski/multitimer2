package pl.grizwold.multitimer.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grizwold.multitimer.logic.model.Timer;

import java.util.List;
import java.util.UUID;

@Service
public class TimerService {
    private final TimerDao timerDao;

    @Autowired
    public TimerService(TimerDao timerDao) {
        this.timerDao = timerDao;
    }

    List<Timer> getExpiredTimers() {
        return timerDao.getExpired();
    }

    public UUID createTimer(Timer timer) {
        return timerDao.save(timer);
    }

    public void finishTimer(UUID id) {
        this.timerDao.markAsFinished(id);
    }

    public UUID cancelTimer(UUID uuid) {
        this.timerDao.stop(uuid);
        return uuid;
    }

    public Timer extend(long durationAdded, UUID id) {
        return null;
    }

    public Timer shorten(long durationSubtracted, UUID id) {
        return null;
    }

    public Timer rename(String name, UUID id) {
        return null;
    }
}
