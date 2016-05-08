package de.steenken.combatrunner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by dominik on 08.05.16.
 */
public class DiceRollerTest extends Application {
    @Override
    public void start(Stage mainWindow) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui/DiceRoller.fxml"));
        mainWindow.setTitle("DiceRollerTest");
        mainWindow.setScene(new Scene(root));
        mainWindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
