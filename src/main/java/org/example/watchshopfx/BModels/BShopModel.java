package org.example.watchshopfx.BModels;

import org.example.watchshopfx.Models.ShopModel;

public class BShopModel {
    private static final ShopModel shopModel = new ShopModel();

    public static ShopModel build() {
        return shopModel;
    }
}

