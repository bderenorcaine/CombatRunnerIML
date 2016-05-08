package de.steenken.combatrunner.ui;

import de.steenken.combatrunner.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by dominik on 27.12.15.
 */
public class UIController {

    private static UIController instance = new UIController();

    private static Parent rootScene = null;

    private static Stage characterDialog = null;

    private static void makeCharacterDialog() {
        try {
            rootScene = FXMLLoader.load(Main.class.getResource("ui/characterEditor.fxml"));
            characterDialog = new Stage();
            characterDialog.setTitle("Add Characters");
            characterDialog.setScene(new Scene(rootScene));
        } catch (IOException e) {
            showMessage("Could not find ui definition for character editor - this means the program is broken");
        }
    }

    public static void showCharacterDialog() {
        if (characterDialog == null) {
            makeCharacterDialog();
        }
        if (characterDialog != null) {
            characterDialog.show();
        }
    }

    public static void hideCharacterDialog() {
        if (characterDialog == null) {
            makeCharacterDialog();
        }
        if (characterDialog != null) {
            characterDialog.hide();
        }
    }

    public static String getTextFromUser(final String title, final String header, final String content) {
        TextInputDialog dialog = new TextInputDialog(title);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        } else {
            return "0";
        }
    }

    public static void showMessage(final String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("Message reads:");
        alert.setContentText(message);

        alert.showAndWait();
    }
}
