package org.example.watchshopfx.Models;

import org.example.watchshopfx.IObserver;

import java.util.ArrayList;
import java.util.List;

public class ConsoleModel {
    private final ArrayList<String> messages = new ArrayList<>();
    private final ArrayList<IObserver> allObserver = new ArrayList<>();

    public void addObserver(IObserver observer) {
        allObserver.add(observer);
        eventCall();
    }

    public List<String> getMessages() {
        return List.copyOf(messages);
    }

    public void addMessage(String message) {
        messages.add(0, message);
        eventCall();
    }

    public void clear() {
        messages.clear();
        eventCall();
    }

    private void eventCall() {
        allObserver.forEach(action -> action.event(this));
    }
}

