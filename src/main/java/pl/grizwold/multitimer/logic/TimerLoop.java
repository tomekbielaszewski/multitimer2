package pl.grizwold.multitimer.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.grizwold.multitimer.gui.model.GuiEvent;
import pl.grizwold.multitimer.logic.event.handlers.GuiEventHandler;
import pl.grizwold.multitimer.logic.model.LogicEvent;
import pl.grizwold.multitimer.logic.model.Timer;
import pl.grizwold.multitimer.logic.model.dao.TimerRepository;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class TimerLoop {
    private final Set<GuiEventHandler> guiEventHandlers;
    private final Queue<GuiEvent> guiEventQueue;
    private final Queue<LogicEvent> logicEventQueue;
    private final Queue<Timer> timers;

    @Autowired
    public TimerLoop(Queue<GuiEvent> guiEventQueue, Queue<LogicEvent> logicEventQueue, Queue<Timer> timers, Set<GuiEventHandler> guiEventHandlers, TimerRepository timerRepository) {
        this.guiEventQueue = guiEventQueue;
        this.logicEventQueue = logicEventQueue;
        this.guiEventHandlers = guiEventHandlers;
        this.timers = timers;
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(this::run, 10, 200, TimeUnit.MILLISECONDS);
    }

    private void run() {
        while (true) {
            updateTimers();
            processGuiEvents();
        }
    }

    private void updateTimers() {
        List<LogicEvent> events = timers.stream()
                .filter(Timer::isRunning)
                .map(Timer::update)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        logicEventQueue.addAll(events);
    }

    private void processGuiEvents() {
        Optional.ofNullable(guiEventQueue.poll())
                .ifPresent(event -> guiEventHandlers.stream()
                        .filter(handler -> handler.accepted(event))
                        .forEach(handler -> handler.execute(event))
                );
    }
}
