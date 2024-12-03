package org.game.model;

import org.game.controller.Command;

public class AcheterBatimentCommand implements Command {

    @Override
    public void exec(Model model) {
        model.setNextEvent(Event.ACHETER_BATIMENT);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AcheterBatimentCommand{");
        sb.append('}');
        return sb.toString();
    }

}
