package org.game.model;

import javafx.application.Platform;
import javafx.concurrent.Task;

public class Model {

    private GameManager gameManager;
    private Event nextEvent;
    private ModelListener listener;
    private Task<Void> modelTask;
    private Thread modelThread;  // Thread pour exécuter la Task

    public Model() {
        gameManager = new GameManager();
        startModelTask(); // Démarrer la tâche pour afficher l'état

    }

    // Méthode pour démarrer une Task qui incremente les ressources selon les batiments
    private void startModelTask() {
        modelTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (!isCancelled()) {
                    update();
                    Thread.sleep(1000);
                }
                return null;
            }
        };

        modelThread = new Thread(modelTask);
        modelThread.setDaemon(true); // Le thread s'arrête lorsque l'application se termine
        modelThread.start();
    }

    public void setListener(ModelListener listener) {
        this.listener = listener;
    }

    public Event getNextEvent() {
        return nextEvent;
    }

    public void setNextEvent(Event nextEvent) {
        this.nextEvent = nextEvent;
        gameManager.handle(this);
    }

    public void update() {
        if (listener != null) {
            Platform.runLater(() -> {
                listener.update(gameManager);
            });
        }
    }


}
