package sample;

import io.vertx.core.json.JsonObject;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.model.Message;

import java.util.UUID;

public class Lobby extends ChatController {
    public TextArea sentMsg;
    public AnchorPane lobbyAnchorPane;
    public TextField sendMsg;
    public Button send;
    public ListView userList;
    public Button exitBtn;
    public Label appMsg;


    Message message;
    String lobbyName;
    String acc;
    UUID lobbyId;


    public TextField getSendMsg() {
        return sendMsg;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public String getAcc() {
        return acc;
    }


    public UUID getLobbyId(){
        return lobbyId;
    }

    public void setText(String text){
        sentMsg.appendText(text + '\n');

    }

    public void sendMessage(ActionEvent actionEvent) {

        appMsg.setText("");

        lobbyId = getId();
        String text = getSendMsg().getText();
        String username = getUser();

        if(text.isEmpty()){
            Exception e;
            appMsg.setText("Can't send an empty string");
        }
        else {
            sendMessage(lobbyId, username, text);
            sendMsg.setText("");
            setText(text);
        }
    }

    public void onExit(ActionEvent actionEvent) {
        Stage oldStage = (Stage) exitBtn.getScene().getWindow();
        oldStage.close();
        System.exit(0);
    }
}
