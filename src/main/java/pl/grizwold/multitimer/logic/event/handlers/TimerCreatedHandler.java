package pl.grizwold.multitimer.logic.event.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.gui.model.GuiEvent;
import pl.grizwold.multitimer.gui.model.TimerCreated;
import pl.grizwold.multitimer.logic.model.Timer;
import pl.grizwold.multitimer.logic.model.dao.TimerRepository;

import java.util.Queue;

@Component
public class TimerCreatedHandler implements GuiEventHandler {
    private TimerRepository timerRepository;
    private Queue<Timer> timers;

    @Autowired
    public TimerCreatedHandler(TimerRepository timerRepository, Queue<Timer> timers) {
        this.timerRepository = timerRepository;
        this.timers = timers;
    }

    @Override
    public boolean accepted(GuiEvent event) {
        return event instanceof TimerCreated;
    }

    @Override
    public void execute(GuiEvent event) {
        TimerCreated _event = (TimerCreated) event;
        Timer timer = Timer.builder()
                .name(_event.getName())
                .timestamp(_event.getFinishTimestamp())
                .build();

        Timer savedTimer = timerRepository.save(timer);
        timers.add(savedTimer);
    }
}
