package sample;

import io.vertx.core.json.JsonObject;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.model.Message;

import java.util.UUID;

public class Lobby extends ChatController {
    public TextArea sentMsg;
    public AnchorPane lobbyAnchorPane;
    public TextField sendMsg;
    public Button send;
    public ListView userList;


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

    public void sendMessage(ActionEvent actionEvent) {

        String text = getSendMsg().getText();
        String lobby = getLobby();
        String username = getUser();


        sendMessage(lobby, username, text);
        sendMsg.setText("");

    }
}
