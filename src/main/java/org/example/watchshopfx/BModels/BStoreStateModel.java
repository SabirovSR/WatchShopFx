package org.example.watchshopfx.BModels;

import org.example.watchshopfx.Models.StoreStateModel;

public class BStoreStateModel {
    private static final StoreStateModel storeStateModel = new StoreStateModel();

    public static StoreStateModel build() {
        return storeStateModel;
    }
}

