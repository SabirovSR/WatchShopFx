package org.example.watchshopfx;

import org.example.watchshopfx.Models.ConsoleModel;
import org.example.watchshopfx.Models.StoreStateModel;
import org.example.watchshopfx.Models.ShopModel;

public interface IObserver {
    void event(ShopModel m);
    void event(ConsoleModel c);
    void event(StoreStateModel e);
}

