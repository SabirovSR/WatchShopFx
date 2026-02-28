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

public class InventoryCostStatisticsViewController implements IObserver {
    private final ShopModel shopModel = BShopModel.build();
    private final Font monoFont = Font.font("monospaced", 12);

    @FXML
    VBox inventoryStatsBox;

    @FXML
    void initialize() {
        shopModel.addObserver(this);
    }

    @Override
    public void event(ShopModel model) {
        inventoryStatsBox.getChildren().clear();

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-16s | %-8s | %s%n", "Brand", "Cost", "Time"));
        sb.append("-----------------+----------+-------\n");

        for (Watch watch : model.getWatchesByCostDesc()) {
            sb.append(String.format("%-16s | %-8d | %s%n", watch.getBrand(), watch.getCost(), watch.getTimeString()));
        }

        Text text = new Text(sb.toString());
        text.setFont(monoFont);
        inventoryStatsBox.getChildren().add(new VBox(text));
    }

    @Override
    public void event(ConsoleModel c) {
    }

    @Override
    public void event(StoreStateModel e) {
    }
}
