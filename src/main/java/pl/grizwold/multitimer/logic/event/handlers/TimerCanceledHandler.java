package pl.grizwold.multitimer.logic.event.handlers;

import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.gui.model.GuiEvent;
import pl.grizwold.multitimer.gui.model.TimerCanceled;
import pl.grizwold.multitimer.logic.model.Timer;
import pl.grizwold.multitimer.logic.model.dao.TimerRepository;

import java.util.Queue;

@Component
public class TimerCanceledHandler implements GuiEventHandler {
    private TimerRepository timerRepository;
    private Queue<Timer> timers;

    public TimerCanceledHandler(TimerRepository timerRepository, Queue<Timer> timers) {
        this.timerRepository = timerRepository;
        this.timers = timers;
    }

    @Override
    public boolean accepted(GuiEvent event) {
        return event instanceof TimerCanceled;
    }

    @Override
    public void execute(GuiEvent event) {
        TimerCanceled _event = (TimerCanceled) event;
        timers.stream()
                .filter(t -> t.getId().equals(_event.getId()))
                .findFirst()
                .ifPresent(t -> {
                    this.timerRepository.markAsFinished(t.getId());
                    this.timers.clear();
                    this.timers.addAll(this.timerRepository.findByFinished(true));
                });

    }
}
