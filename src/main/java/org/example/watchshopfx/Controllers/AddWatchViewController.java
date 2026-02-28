package org.example.watchshopfx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.watchshopfx.BModels.BConsoleModel;
import org.example.watchshopfx.BModels.BStoreStateModel;
import org.example.watchshopfx.BModels.BShopModel;
import org.example.watchshopfx.IObserver;
import org.example.watchshopfx.InvalidWatchTimeException;
import org.example.watchshopfx.Models.ConsoleModel;
import org.example.watchshopfx.Models.StoreStateModel;
import org.example.watchshopfx.Models.ShopModel;
import org.example.watchshopfx.Watch;

public class AddWatchViewController implements IObserver {
    private final ShopModel shopModel = BShopModel.build();
    private final ConsoleModel consoleModel = BConsoleModel.build();
    private final StoreStateModel storeStateModel = BStoreStateModel.build();

    @FXML
    ComboBox<String> brandComboBox;
    @FXML
    TextField timeField;
    @FXML
    TextField costField;
    @FXML
    Button addButton;

    @FXML
    void initialize() {
        shopModel.addObserver(this);
        brandComboBox.setEditable(true);
        timeField.setPromptText("ЧЧ:ММ");
        timeField.setText("12:00");
        costField.setPromptText("Стоимость");
        refreshBrandOptions();
    }

    @FXML
    void add() {
        String brand = brandComboBox.getEditor().getText() == null
                ? ""
                : brandComboBox.getEditor().getText().trim();

        if (brand.isEmpty() && brandComboBox.getValue() != null) {
            brand = brandComboBox.getValue().trim();
        }

        if (brand.isEmpty()) {
            consoleModel.addMessage("Ошибка: марка часов не задана");
            return;
        }

        String time = timeField.getText();
        String costText = costField.getText();

        try {
            int cost = Integer.parseInt(costText.trim());
            if (storeStateModel.getCashBalance() < cost) {
                consoleModel.addMessage("Ошибка: недостаточно капитала для закупки");
                return;
            }
            Watch watch = new Watch(brand, cost, time);
            shopModel.addWatch(watch);
            storeStateModel.onWatchAdded(watch);
            consoleModel.addMessage("Добавлены часы: " + watch.getBrand() + ", " + watch.getTimeString() + ", " + watch.getCost());
            costField.clear();
        } catch (NumberFormatException e) {
            consoleModel.addMessage("Ошибка: стоимость должна быть целым числом");
        } catch (InvalidWatchTimeException e) {
            storeStateModel.onInvalidTimeInput();
            consoleModel.addMessage("Ошибка времени при добавлении: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            consoleModel.addMessage("Ошибка: " + e.getMessage());
        }
    }

    private void refreshBrandOptions() {
        String currentText = brandComboBox.getEditor().getText();
        brandComboBox.getItems().setAll(shopModel.getBrandSuggestionsSorted());
        brandComboBox.getEditor().setText(currentText);
    }

    @Override
    public void event(ShopModel m) {
        refreshBrandOptions();
    }

    @Override
    public void event(ConsoleModel c) {
    }

    @Override
    public void event(StoreStateModel e) {
    }
}


