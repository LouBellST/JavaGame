package org.game.model;

import org.game.controller.Command;

public class AcheterHabitantCommand implements Command {

    @Override
    public void exec(Model model) {
        model.setNextEvent(Event.ACHETER_HABITANT);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AcheterHabitantCommand{");
        sb.append('}');
        return sb.toString();
    }

}
