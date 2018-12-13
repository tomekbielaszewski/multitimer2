package pl.grizwold.multitimer.logic.event.handlers;

import pl.grizwold.multitimer.gui.model.GuiEvent;

public interface GuiEventHandler {
    boolean accepted(GuiEvent event);
    void execute(GuiEvent event);
}
