package org.example.watchshopfx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.example.watchshopfx.BModels.BConsoleModel;
import org.example.watchshopfx.IObserver;
import org.example.watchshopfx.Models.ConsoleModel;
import org.example.watchshopfx.Models.StoreStateModel;
import org.example.watchshopfx.Models.ShopModel;

public class ConsoleViewController implements IObserver {
    private final ConsoleModel consoleModel = BConsoleModel.build();
    private final Font monoFont = Font.font("monospaced", 12);

    @FXML
    VBox consoleMessagesBox;

    @FXML
    void initialize() {
        consoleModel.addObserver(this);
    }

    @Override
    public void event(ConsoleModel c) {
        consoleMessagesBox.getChildren().clear();
        StringBuilder builder = new StringBuilder();
        for (String message : c.getMessages()) {
            builder.append(message).append("\n");
        }
        Text text = new Text(builder.toString());
        text.setFont(monoFont);
        consoleMessagesBox.getChildren().add(new VBox(text));
    }

    @Override
    public void event(StoreStateModel e) {
    }

    @Override
    public void event(ShopModel m) {
    }
}


