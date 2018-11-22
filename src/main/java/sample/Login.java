package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.Message;

import java.io.IOException;

public class Login extends ChatController {
    public AnchorPane rootAnchorPane;
    public Button loginBtn;
    public TextField userNameField;
    public TextField lobbyField;


    public void doLogin(ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        login();


        Stage oldStage = (Stage) loginBtn.getScene().getWindow();
        oldStage.close();

        Parent loader = FXMLLoader.load(getClass().getResource("/LoadingScreen.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(loader, 200, 200));
        stage.show();


    }

    public TextField getUserNameField() {
        return userNameField;
    }

    public TextField getLobbyField() {
        return lobbyField;
    }

    public void login() {

        String lobbyName = getLobbyField().getText();
        String username = getUserNameField().getText();

        doLogin(lobbyName, username);
    }

}
