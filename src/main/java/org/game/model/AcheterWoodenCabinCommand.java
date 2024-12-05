package org.game.model;

import org.game.controller.Command;

public class AcheterWoodenCabinCommand implements Command {

    @Override
    public void exec(Model model) {
        model.setNextEvent(Event.ACHETER_WOODENCABIN);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AcheterBatimentCommand{");
        sb.append('}');
        return sb.toString();
    }

}
