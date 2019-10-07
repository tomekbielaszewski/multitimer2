package pl.grizwold.multitimer.logic.dao;

import org.springframework.stereotype.Service;
import pl.grizwold.multitimer.logic.model.Pause;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PauseDao {
    private List<Pause> pauses = new ArrayList<>();

    public void save(Pause pause) {
        this.pauses.add(pause);
    }

    public Pause get(UUID id) {
        return pauses.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public void remove(UUID id) {
        pauses.remove(get(id));
    }
}
