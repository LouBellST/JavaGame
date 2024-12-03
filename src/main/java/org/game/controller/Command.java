package org.game.controller;

import org.game.model.Model;

public interface Command {

    void exec(Model model);

}