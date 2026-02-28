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

public class StatisticsViewController implements IObserver {
    private final ShopModel shopModel = BShopModel.build();
    private final Font monoFont = Font.font("monospaced", 12);

    @FXML
    VBox brandStatsBox;

    @FXML
    void initialize() {
        shopModel.addObserver(this);
    }

    @Override
    public void event(ShopModel model) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-16s | %s%n", "Brand", "Count"));
        sb.append("-----------------+-------\n");

        for (var entry : model.getBrandStatisticsDesc()) {
            sb.append(String.format("%-16s | %d%n", entry.getKey(), entry.getValue()));
        }

        brandStatsBox.getChildren().clear();
        Text text = new Text(sb.toString());
        text.setFont(monoFont);
        brandStatsBox.getChildren().add(new VBox(text));
    }

    @Override
    public void event(ConsoleModel c) {
    }

    @Override
    public void event(StoreStateModel e) {
    }
}
