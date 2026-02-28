package org.example.watchshopfx.BModels;

import org.example.watchshopfx.Models.ConsoleModel;

public class BConsoleModel {
    private static final ConsoleModel consoleModel = new ConsoleModel();

    public static ConsoleModel build() {
        return consoleModel;
    }
}

