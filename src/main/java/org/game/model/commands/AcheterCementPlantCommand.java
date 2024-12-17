package org.game.model.commands;

import org.game.controller.Command;
import org.game.model.Event;
import org.game.model.Model;

public class AcheterCementPlantCommand implements Command {

    @Override
    public void exec(Model model) {
        model.setNextEvent(Event.ACHETER_CEMENTPLANT);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AcheterBatimentCommand{");
        sb.append('}');
        return sb.toString();
    }

}
