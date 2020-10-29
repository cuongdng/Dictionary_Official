package main;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    @FXML
    public TextField tfNewWord;

    @FXML
    public TextField tfMeaning;

    @FXML
    public Button btnAdd;

    @FXML
    public Label lblSuccess;

    @FXML
    public Label lblFail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAdd.setOnMouseClicked(event -> {
            String newWord = tfNewWord.getText();
            String meaning = tfMeaning.getText();
            DictionaryManagement.addWord(newWord, meaning);
            Stage tmp = (Stage) btnAdd.getParent().getScene().getWindow();
            tmp.close();
        });
    }
}
