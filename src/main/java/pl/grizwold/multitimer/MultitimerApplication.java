package pl.grizwold.multitimer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.grizwold.multitimer.gui.model.GuiEvent;
import pl.grizwold.multitimer.logic.model.LogicEvent;
import pl.grizwold.multitimer.logic.model.Timer;
import pl.grizwold.multitimer.logic.model.dao.TimerRepository;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@SpringBootApplication
public class MultitimerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultitimerApplication.class, args);
    }

    @Bean
    public Queue<GuiEvent> guiEventQueue() {
        return new ConcurrentLinkedQueue<>();
    }

    @Bean
    public Queue<LogicEvent> logicEventQueue() {
        return new ConcurrentLinkedQueue<>();
    }

    @Bean
    public Queue<Timer> timers(TimerRepository timerRepository) {
        return new ConcurrentLinkedQueue<>(timerRepository.findByFinished(true));
    }
}

