package org.example.watchshopfx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.watchshopfx.BModels.BStoreStateModel;
import org.example.watchshopfx.BModels.BShopModel;
import org.example.watchshopfx.IObserver;
import org.example.watchshopfx.Models.ConsoleModel;
import org.example.watchshopfx.Models.StoreStateModel;
import org.example.watchshopfx.Models.ShopModel;

public class ShopStatusViewController implements IObserver {
    private final ShopModel shopModel = BShopModel.build();
    private final StoreStateModel storeStateModel = BStoreStateModel.build();

    @FXML
    Label watchCountLabel;
    @FXML
    Label inventoryCostLabel;
    @FXML
    Label cashBalanceLabel;
    @FXML
    Label invalidTimeErrorsLabel;
    @FXML
    Label soldCountLabel;
    @FXML
    Label soldAmountLabel;

    @FXML
    void initialize() {
        watchCountLabel.setText(String.valueOf(shopModel.countWatches()));
        inventoryCostLabel.setText(String.valueOf(shopModel.totalInventoryCost()));
        cashBalanceLabel.setText(String.valueOf(storeStateModel.getCashBalance()));
        invalidTimeErrorsLabel.setText(String.valueOf(storeStateModel.getInvalidTimeErrors()));
        soldCountLabel.setText(String.valueOf(storeStateModel.getSoldCount()));
        soldAmountLabel.setText(String.valueOf(storeStateModel.getSoldAmount()));

        shopModel.addObserver(this);
        storeStateModel.addObserver(this);
    }

    @Override
    public void event(ShopModel m) {
        watchCountLabel.setText(String.valueOf(m.countWatches()));
        inventoryCostLabel.setText(String.valueOf(m.totalInventoryCost()));
    }

    @Override
    public void event(ConsoleModel c) {
    }

    @Override
    public void event(StoreStateModel e) {
        cashBalanceLabel.setText(String.valueOf(e.getCashBalance()));
        invalidTimeErrorsLabel.setText(String.valueOf(e.getInvalidTimeErrors()));
        soldCountLabel.setText(String.valueOf(e.getSoldCount()));
        soldAmountLabel.setText(String.valueOf(e.getSoldAmount()));
    }
}


