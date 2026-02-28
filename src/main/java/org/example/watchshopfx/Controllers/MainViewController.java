package org.example.watchshopfx.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.example.watchshopfx.App;
import org.example.watchshopfx.BModels.BShopModel;
import org.example.watchshopfx.IObserver;
import org.example.watchshopfx.Models.ConsoleModel;
import org.example.watchshopfx.Models.StoreStateModel;
import org.example.watchshopfx.Models.ShopModel;
import org.example.watchshopfx.Watch;

import java.io.IOException;

public class MainViewController implements IObserver {
    private final ShopModel shopModel = BShopModel.build();

    @FXML
    GridPane main;

    @FXML
    GridPane watchListGrid;

    @FXML
    void initialize() {
        shopModel.addObserver(this);

        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("Views/AddWatchView.fxml"));
            FXMLLoader fxmlLoader2 = new FXMLLoader(App.class.getResource("Views/ConsoleView.fxml"));
            FXMLLoader fxmlLoader3 = new FXMLLoader(App.class.getResource("Views/InventoryCostStatisticsView.fxml"));
            FXMLLoader fxmlLoader4 = new FXMLLoader(App.class.getResource("Views/BulkTimeUpdateView.fxml"));
            FXMLLoader fxmlLoader5 = new FXMLLoader(App.class.getResource("Views/MostExpensiveWatchView.fxml"));
            FXMLLoader fxmlLoader6 = new FXMLLoader(App.class.getResource("Views/StatisticsView.fxml"));
            FXMLLoader fxmlLoader8 = new FXMLLoader(App.class.getResource("Views/ShopStatusView.fxml"));

            Pane pane8 = fxmlLoader8.load();
            Pane pane1 = fxmlLoader1.load();
            Pane pane2 = fxmlLoader2.load();
            Pane pane3 = fxmlLoader3.load();
            Pane pane4 = fxmlLoader4.load();
            Pane pane5 = fxmlLoader5.load();
            Pane pane6 = fxmlLoader6.load();

            VBox vBox = new VBox(2);
            vBox.setSpacing(0);
            vBox.getChildren().addAll(pane4, pane5);

            main.add(pane1, 1, 0);
            main.add(pane2, 1, 2);
            main.add(pane3, 0, 1);
            main.add(vBox, 0, 0);
            main.add(pane6, 1, 1);
            main.add(pane8, 0, 2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void event(ShopModel model) {
        watchListGrid.getChildren().clear();
        int row = 0;
        for (Watch watch : model) {
            WatchViewController watchController = new WatchViewController();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Views/WatchView.fxml"));
            fxmlLoader.setController(watchController);
            try {
                Pane pane = fxmlLoader.load();
                watchController.setWatch(watch);
                watchListGrid.add(pane, 0, row++);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void event(ConsoleModel c) {
    }

    @Override
    public void event(StoreStateModel e) {
    }
}


