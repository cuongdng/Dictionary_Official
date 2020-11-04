package main;

import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URLConnection;
import com.microsoft.cognitiveservices.speech.*;


import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public static String curWord = "";

    @FXML
    public Button btSearch;

    @FXML
    public Button btEdit;

    @FXML
    public Button btnAdd;

    @FXML
    public Button btnDelete;

    @FXML
    public Button btnTrans;

    @FXML
    public Label lblDelete;

    @FXML
    public TextField tfSearchedWord;

    @FXML
    public ListView<String> lvWords;

    @FXML
    public ImageView imgSpeaker;

    @FXML
    public WebView wvMeaning;
//
//    @FXML
//    private void btnEditClickedAction(ActionEvent event) throws IOException {
//        Stage addWindow = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("EditWindow.fxml"));
//        addWindow.setTitle("Edit Word");
//        addWindow.setScene(new Scene(root, 300, 175));
//        addWindow.show();
//    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DictionaryManagement.insertFromFile();
        //        DictionaryManagement.insertFromFile();
        lvWords.getItems().addAll(Dictionary.listWords.keySet());

        //Xu ly thanh search
        tfSearchedWord.setOnKeyTyped(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                System.out.println("Search!!!");
                String searchedWord = tfSearchedWord.getText();
                if (searchedWord != null && !searchedWord.equals("")) {
                    System.out.println("Searched World: " + searchedWord);
                    searchedWord = searchedWord.trim().toLowerCase();
                    String wordMeaning = DictionaryManagement.dictionaryLookup(searchedWord);
                    curWord = searchedWord;
                    WebEngine webEngine = wvMeaning.getEngine();
                    webEngine.loadContent(wordMeaning);
                }
            }
            lvWords.getItems().clear();
            String searchedWord = tfSearchedWord.getText();
            if (searchedWord != null && !searchedWord.equals("")) {
                    lvWords.getItems().addAll(DictionaryCommandline.dictionarySearcher(searchedWord).keySet());
                }
            if (tfSearchedWord.getText().isEmpty()) {
                lvWords.getItems().addAll(DictionaryCommandline.dictionarySearcher(searchedWord).keySet());
            }
        });

        //TextToSpeech
        imgSpeaker.setOnMouseClicked(event -> {
            try {
                URL url = new URL("https://www.google.com");
                URLConnection connection = url.openConnection();
                connection.connect();

                String speechSubscriptionKey = "4f343acb8f304d60ae2bb748d910b57e";
                String serviceRegion = "southeastasia";
                SpeechConfig speechConfig = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
                AudioConfig audioConfig = AudioConfig.fromDefaultSpeakerOutput();

                SpeechSynthesizer synthesizer = new SpeechSynthesizer(speechConfig, audioConfig);
                synthesizer.SpeakText(curWord);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText(null);
                alert.setContentText("No internet connection!");

                alert.showAndWait();
                System.out.println("No internet!");
            }
        });

        //Click tung tu
        btSearch.setOnMouseClicked(event -> {
            System.out.println("Search!!!");
            String searchedWord = tfSearchedWord.getText();
            if (searchedWord != null && !searchedWord.equals("")) {
                System.out.println("Searched World: " + searchedWord);
                searchedWord = searchedWord.trim().toLowerCase();
                curWord = searchedWord;
                String wordMeaning = DictionaryManagement.dictionaryLookup(searchedWord);
                WebEngine webEngine = wvMeaning.getEngine();
                webEngine.loadContent(wordMeaning);
            }
        });

        //Edit Button
        btEdit.setOnMouseClicked(event -> {
            Stage addWindow = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/EditWindow.fxml"));
                addWindow.setTitle("Edit Word");
                addWindow.setScene(new Scene(root, 300, 120));
                addWindow.getIcons().add(new Image(Main.class.getResourceAsStream("/images/icon.png")));
                addWindow.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            WebEngine webEngine = wvMeaning.getEngine();
            webEngine.loadContent(DictionaryManagement.dictionaryLookup(curWord));
        });

        //Open Trans Window
        btnTrans.setOnMouseClicked(event -> {
            Stage addWindow = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/TranslateWindow.fxml"));
                addWindow.setTitle("Transliterate");
                addWindow.setScene(new Scene(root, 600, 450));
                addWindow.getIcons().add(new Image(Main.class.getResourceAsStream("/images/icon.png")));
                addWindow.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        //Click tu tren list
        lvWords.setOnMouseClicked(event -> {
            String searchedWord = lvWords.getSelectionModel().getSelectedItem();
            if (searchedWord != null && !searchedWord.equals("")) {
                System.out.println("Searched World: " + searchedWord);
                searchedWord = searchedWord.trim().toLowerCase();
                curWord = searchedWord;
                String wordMeaning = DictionaryManagement.dictionaryLookup(searchedWord);
                WebEngine webEngine = wvMeaning.getEngine();
                webEngine.loadContent(wordMeaning);
                lblDelete.setVisible(false);
            }
        });

        //Add Button
        btnAdd.setOnMouseClicked(event -> {
            Stage addWindow = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddWindow.fxml"));
                addWindow.setTitle("Add Word");
                addWindow.setScene(new Scene(root, 300, 180));
                addWindow.getIcons().add(new Image(Main.class.getResourceAsStream("/images/icon.png")));
                addWindow.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        //Delete Button
        lblDelete.setVisible(false);
        btnDelete.setOnMouseClicked(event -> {
            if(DictionaryManagement.deleteWord(curWord)) {
                lvWords.getItems().remove(curWord);
                lblDelete.setVisible(true);
            }
        });
    }
}
