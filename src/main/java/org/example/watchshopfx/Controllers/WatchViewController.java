package org.example.watchshopfx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.watchshopfx.BModels.BConsoleModel;
import org.example.watchshopfx.BModels.BStoreStateModel;
import org.example.watchshopfx.BModels.BShopModel;
import org.example.watchshopfx.InvalidWatchTimeException;
import org.example.watchshopfx.Models.ConsoleModel;
import org.example.watchshopfx.Models.StoreStateModel;
import org.example.watchshopfx.Models.ShopModel;
import org.example.watchshopfx.Watch;

public class WatchViewController {
    private final ShopModel shopModel = BShopModel.build();
    private final ConsoleModel consoleModel = BConsoleModel.build();
    private final StoreStateModel storeStateModel = BStoreStateModel.build();

    private Watch watch;

    @FXML
    Label brandLabel;
    @FXML
    Label costLabel;
    @FXML
    Label timeLabel;
    @FXML
    TextField setTimeField;

    public void setWatch(Watch watch) {
        this.watch = watch;
        refreshView();
    }

    @FXML
    void remove() {
        if (watch == null) {
            return;
        }
        shopModel.removeWatch(watch);
        storeStateModel.onWatchSold(watch);
        consoleModel.addMessage("Продажа: " + watch.getBrand() + " за " + watch.getCost());
    }

    @FXML
    void setTime() {
        if (watch == null) {
            return;
        }
        try {
            watch.setTime(setTimeField.getText());
            shopModel.notifyChanged();
            consoleModel.addMessage("Время обновлено для " + watch.getBrand() + ": " + watch.getTimeString());
        } catch (InvalidWatchTimeException e) {
            storeStateModel.onInvalidTimeInput();
            consoleModel.addMessage("Ошибка времени для " + watch.getBrand() + ": " + e.getMessage());
        }
    }

    private void refreshView() {
        if (watch == null) {
            return;
        }
        brandLabel.setText(watch.getBrand());
        costLabel.setText(String.valueOf(watch.getCost()));
        timeLabel.setText(watch.getTimeString());
        if (setTimeField != null) {
            setTimeField.setText(watch.getTimeString());
        }
    }
}


