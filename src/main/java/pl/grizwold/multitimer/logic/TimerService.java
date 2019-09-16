package pl.grizwold.multitimer.logic;

import org.springframework.beans.factory.annotation.Autowired;
import pl.grizwold.multitimer.logic.model.Timer;

import java.util.List;
import java.util.UUID;

public class TimerService {
    private final TimerDao timerDao;

    @Autowired
    public TimerService(TimerDao timerDao) {
        this.timerDao = timerDao;
    }

    public List<Timer> getExpiredTimers() {
        return timerDao.getExpired();
    }

    public UUID createTimer(Timer timer) {
        return timerDao.save(timer);
    }

    public void finishTimer(UUID id) {
        this.timerDao.markAsFinished(id);
    }
}
