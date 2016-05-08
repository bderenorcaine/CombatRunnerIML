package de.steenken.combatrunner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage mainWindow) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui/combatrunner.fxml"));
        mainWindow.setTitle("CombatRunner v0.1-alpha");
        mainWindow.setScene(new Scene(root));
        mainWindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
