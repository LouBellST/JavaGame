package org.game.controller;

import javafx.concurrent.Task;
import org.game.model.Model;

import java.util.LinkedList;
import java.util.Queue;

public class BagOfCommands {

    private final Queue<Command> commands = new LinkedList<>();
    private final Model model;
    private Task<Void> commandTask; // Task
    private Thread commandThread;  // Thread pour exécuter la Task

    public BagOfCommands(Model model) {
        this.model = model;
        startStateLogger(); // Démarrer la tâche pour afficher l'état
    }

    public void add(Command command) {
        System.err.println("On ajoute une commande");
        commands.add(command);
    }

    // Méthode pour démarrer une Task qui affiche l'état périodiquement
    private void startStateLogger() {
        commandTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (!isCancelled()) {
                    if (!commands.isEmpty()) {
                        Command command = commands.poll();
                        System.err.printf("On traite la commande %s\n", command);
                        command.exec(model);
                    }
                    Thread.sleep(100);
                }
                return null;
            }
        };

        commandThread = new Thread(commandTask);
        commandThread.setDaemon(true); // Le thread s'arrête lorsque l'application se termine
        commandThread.start();
    }

    // Méthode pour arrêter la Task proprement
    public void stopStateLogger() {
        if (commandTask != null) {
            commandTask.cancel(); // Annuler la tâche
        }
    }
}
