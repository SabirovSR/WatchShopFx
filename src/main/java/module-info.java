module org.example.watchshopfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
  requires jdk.jfr;

  opens org.example.watchshopfx to javafx.fxml;
    exports org.example.watchshopfx;
    exports org.example.watchshopfx.Controllers;
    opens org.example.watchshopfx.Controllers to javafx.fxml;
  exports org.example.watchshopfx.Models;
  opens org.example.watchshopfx.Models to javafx.fxml;
  exports org.example.watchshopfx.BModels;
  opens org.example.watchshopfx.BModels to javafx.fxml;
}
