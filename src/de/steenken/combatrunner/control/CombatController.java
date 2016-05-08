package de.steenken.combatrunner.control;

import de.steenken.combatrunner.data.Combatant;
import de.steenken.combatrunner.data.InitiativeList;
import de.steenken.combatrunner.ui.AskTheUser;
import de.steenken.combatrunner.ui.UIController;
import de.steenken.combatrunner.util.EndOfRoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by dominik on 12/26/15.
 */
public class CombatController {

    private InitiativeList initiativeList;

    @FXML
    private ListView<Combatant> initiativeListDisplay = new ListView<>();

    @FXML
    private TextField scNameField = new TextField();

    @FXML
    private TextField nscNameField = new TextField();

    @FXML
    private TextField nscBaseIniField = new TextField();

    @FXML
    private TextField nscIniDiceField = new TextField();

    public CombatController() {


    }

    public void initialize() {
        initiativeList = new InitiativeList();
        Combatant comb1 = Combatant.makeNSC("Slow Poke", 6, 1);
        Combatant comb2 = Combatant.makeSC("Average Joe", new AskTheUser());
        Combatant comb3 = Combatant.makeNSC("Fast Jack", 13, 4);
        initiativeList.addCombatant(comb1);
        initiativeList.addCombatant(comb2);
        initiativeList.addCombatant(comb3);
//        initiativeList.nextRound();
        initiativeListDisplay.setItems(initiativeList.getReadOnlyList());
        initiativeListDisplay.refresh();
    }

    public void nextRound() {
        System.out.println("Next Round!");
        initiativeList.nextRound();
    }

    public void showCharacterDialog() {
        UIController.showCharacterDialog();
    }

    public void addNSC() {
        initiativeList.addCombatant(Combatant.makeNSC(nscNameField.getText(), Integer.parseInt(nscBaseIniField.getText()), Integer.parseInt(nscIniDiceField.getText())));
        System.out.println(initiativeList);
        System.out.println(initiativeListDisplay.getItems().size());
        System.out.println(initiativeList.getReadOnlyList().size());
        initiativeListDisplay.refresh();
    }

    public void addSC() {
        initiativeList.addCombatant(Combatant.makeSC(scNameField.getText(), new AskTheUser()));
        System.out.println(initiativeList);
        System.out.println(initiativeListDisplay.getItems().size());
        System.out.println(initiativeList.getReadOnlyList().size());
        initiativeListDisplay.refresh();
    }

    public void nextAction() {
        try {
            initiativeList.nextCombatant().act(10);
        } catch (EndOfRoundException e) {
            nextRound();
        }
    }

}
