package pl.grizwold.multitimer.logic.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.grizwold.multitimer.logic.model.Timer;

import java.util.List;

public interface TimerRepository extends CrudRepository<Timer, Long> {
    List<Timer> findByFinished(boolean finished);

    @Query("Update Timer t SET t.finished = true WHERE t.id = :id")
    void markAsFinished(@Param("id") Long id);
}
