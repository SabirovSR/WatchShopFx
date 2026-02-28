package org.example.watchshopfx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.watchshopfx.BModels.BConsoleModel;
import org.example.watchshopfx.BModels.BStoreStateModel;
import org.example.watchshopfx.BModels.BShopModel;
import org.example.watchshopfx.InvalidWatchTimeException;
import org.example.watchshopfx.Models.ConsoleModel;
import org.example.watchshopfx.Models.StoreStateModel;
import org.example.watchshopfx.Models.ShopModel;

public class BulkTimeUpdateViewController {
    private final ShopModel shopModel = BShopModel.build();
    private final ConsoleModel consoleModel = BConsoleModel.build();
    private final StoreStateModel storeStateModel = BStoreStateModel.build();

    @FXML
    TextField allTimeField;

    @FXML
    void start() {
        try {
            shopModel.setTimeForAll(allTimeField.getText());
            consoleModel.addMessage("На всех часах установлено время: " + allTimeField.getText());
        } catch (InvalidWatchTimeException e) {
            storeStateModel.onInvalidTimeInput();
            consoleModel.addMessage("Ошибка массовой установки времени: " + e.getMessage());
        }
    }
}


