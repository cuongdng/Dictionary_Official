package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    @FXML
    public Button btnEdit;

    @FXML
    public TextField tfWordToEdit;

    @FXML
    public TextField tfEditWordTo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnEdit.setOnMouseClicked(event -> {
            String EditWordTo = tfEditWordTo.getText();
            DictionaryManagement.editWord(MainController.curWord, EditWordTo);
            Stage tmp = (Stage) btnEdit.getParent().getScene().getWindow();
            tmp.close();
        });
    }

}
