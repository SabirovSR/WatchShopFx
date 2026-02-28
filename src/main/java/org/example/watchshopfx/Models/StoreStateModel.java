package org.example.watchshopfx.Models;

import org.example.watchshopfx.IObserver;
import org.example.watchshopfx.Watch;

import java.util.ArrayList;

public class StoreStateModel {
    private static final int INITIAL_CAPITAL = 100_000;

    private final ArrayList<IObserver> allObserver = new ArrayList<>();
    private int invalidTimeErrors = 0;
    private int soldCount = 0;
    private int soldAmount = 0;
    private int cashBalance = INITIAL_CAPITAL;

    public void addObserver(IObserver observer) {
        allObserver.add(observer);
        eventCall();
    }

    public int getInitialCapital() {
        return INITIAL_CAPITAL;
    }

    public int getInvalidTimeErrors() {
        return invalidTimeErrors;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public int getSoldAmount() {
        return soldAmount;
    }

    public int getCashBalance() {
        return cashBalance;
    }

    public void onWatchAdded(Watch watch) {
        cashBalance -= watch.getCost();
        eventCall();
    }

    public void onWatchSold(Watch watch) {
        cashBalance += watch.getCost();
        soldCount++;
        soldAmount += watch.getCost();
        eventCall();
    }

    public void onInvalidTimeInput() {
        invalidTimeErrors++;
        eventCall();
    }

    public void resetFinance() {
        invalidTimeErrors = 0;
        soldCount = 0;
        soldAmount = 0;
        cashBalance = INITIAL_CAPITAL;
        eventCall();
    }

    private void eventCall() {
        allObserver.forEach(action -> action.event(this));
    }
}

