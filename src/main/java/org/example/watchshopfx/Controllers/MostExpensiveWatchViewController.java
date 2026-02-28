package org.example.watchshopfx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.example.watchshopfx.BModels.BShopModel;
import org.example.watchshopfx.IObserver;
import org.example.watchshopfx.Models.ConsoleModel;
import org.example.watchshopfx.Models.StoreStateModel;
import org.example.watchshopfx.Models.ShopModel;
import org.example.watchshopfx.Watch;

public class MostExpensiveWatchViewController implements IObserver {
    private final ShopModel shopModel = BShopModel.build();
    private final Font monoFont = Font.font("monospaced", 12);

    @FXML
    VBox expensiveList;

    @FXML
    void initialize() {
        shopModel.addObserver(this);
    }

    @Override
    public void event(ShopModel model) {
        expensiveList.getChildren().clear();
        StringBuilder sb = new StringBuilder();
        for (Watch watch : model.getMostExpensiveWatches()) {
            sb.append(String.format("%s: %d (%s)%n", watch.getBrand(), watch.getCost(), watch.getTimeString()));
        }
        if (sb.isEmpty()) {
            sb.append("Нет часов в магазине");
        }
        Text text = new Text(sb.toString());
        text.setFont(monoFont);
        expensiveList.getChildren().add(text);
    }

    @Override
    public void event(ConsoleModel c) {
    }

    @Override
    public void event(StoreStateModel e) {
    }
}


