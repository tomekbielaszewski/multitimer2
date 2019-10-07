package pl.grizwold.multitimer.logic;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grizwold.multitimer.logic.dao.PauseDao;
import pl.grizwold.multitimer.logic.dao.TimerDao;
import pl.grizwold.multitimer.logic.model.Pause;
import pl.grizwold.multitimer.logic.model.Timer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.SECONDS;

@Slf4j
@Service
public class TimerService {
    private final TimerDao timerDao;
    private final PauseDao pauseDao;

    @Autowired
    public TimerService(TimerDao timerDao, PauseDao pauseDao) {
        this.timerDao = timerDao;
        this.pauseDao = pauseDao;
    }

    List<Timer> getExpiredTimers() {
        return timerDao.getExpired();
    }

    public Timer createTimer(UUID id, String name, long duration) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime finish = now.plus(duration, SECONDS);

        if (Strings.isBlank(name)) {
            name = createName(id, duration);
        }

        log.info("Creating timer {}", name);

        Timer timer = Timer.builder()
                .id(id)
                .name(name)
                .creation(now)
                .finish(finish)
                .build();
        timerDao.save(timer);

        return timer;
    }

    public void finishTimer(UUID id) {
        log.info("Timer {} finished", id);
        this.timerDao.markAsFinished(id);
    }

    public UUID cancelTimer(UUID id) {
        log.info("Timer {} cancelled", id);
        this.timerDao.stop(id);
        this.finishTimer(id);
        return id;
    }

    public Timer extend(long durationAdded, UUID id) {
        Timer timer = timerDao.get(id);

        log.info("Timer {} extended by {} seconds", timer.getName(), durationAdded);

        LocalDateTime newFinishDate = timer.getFinish().plus(durationAdded, SECONDS);
        timer.setFinish(newFinishDate);

        timerDao.save(timer);

        return timer;
    }

    public Timer shorten(long durationSubtracted, UUID id) {
        Timer timer = timerDao.get(id);

        log.info("Timer {} shortened by {} seconds", timer.getName(), durationSubtracted);

        LocalDateTime newFinishDate = timer.getFinish().minus(durationSubtracted, SECONDS);
        timer.setFinish(newFinishDate);

        timerDao.save(timer);

        return timer;
    }

    public Timer rename(String name, UUID id) {
        Timer timer = timerDao.get(id);

        log.info("Timer {} renamed to {}", timer.getName(), name);

        if (Strings.isBlank(name)) {
            long duration = timer.getCreation().until(timer.getFinish(), SECONDS);
            name = createName(id, duration);
        }
        timer.setName(name);

        timerDao.save(timer);

        return timer;
    }

    public UUID pause(UUID id) {
        timerDao.markAsPaused(true, id);
        Timer timer = timerDao.get(id);

        log.info("Timer {} paused", timer.getName());

        LocalDateTime now = LocalDateTime.now();
        long durationLeft = now.until(timer.getFinish(), SECONDS);
        Pause pause = Pause.builder()
                .id(id)
                .durationLeft(durationLeft)
                .build();
        pauseDao.save(pause);

        return id;
    }

    public Timer resume(UUID id) {
        timerDao.markAsPaused(false, id);
        Pause pause = pauseDao.get(id);
        Timer timer = timerDao.get(id);

        log.info("Timer {} resumed", timer.getName());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newFinishDate = now.plus(pause.getDurationLeft(), SECONDS);
        timer.setFinish(newFinishDate);

        timerDao.save(timer);
        pauseDao.remove(id);
        return timer;
    }

    private String createName(UUID id, long duration) {
        return id.toString().substring(0, 5) + ":" + duration;
    }
}
