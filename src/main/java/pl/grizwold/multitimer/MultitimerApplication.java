package pl.grizwold.multitimer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.grizwold.multitimer.ui.TimerControl;

@EnableScheduling
@SpringBootApplication
public class MultitimerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MultitimerApplication.class, args);
        TimerControl control = context.getBean(TimerControl.class);
        control.newTimer();
    }
}

