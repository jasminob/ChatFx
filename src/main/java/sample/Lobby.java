package sample;

import io.reactivex.Observable;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.cells.MessageCell;
import sample.cells.UserCell;
import sample.model.Message;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Lobby extends ChatController implements Initializable {

    public AnchorPane lobbyAnchorPane;
    public TextField sendMsg;
    public Button send;
    public ListView userList;

    public Button exitBtn;
    public Label appMsg;
    public ListView<Message> sentMsg;

    private Message message;
    private String lobbyName;
    private String acc;
    private UUID lobbyId;

    private ObservableList<Message> messages = FXCollections.observableArrayList();
    private ObservableList<sample.model.Lobby> users = FXCollections.observableArrayList();

    private static String text = "";


    public Lobby() {

        JsonArray x = getMessages(getId(), 2, 5);
        for(int i = 0; i < x.size(); i++){
            JsonObject obj = x.getJsonObject(i);
            messages.add(0, Message.fromJson(obj));
        //    sentMsg.appendText(obj.getString("text"));

        }

        JsonArray userArr = getUsers(getId());

        for(int i = 0; i < userArr.size(); i++){
            JsonObject obj = userArr.getJsonObject(i);
            users.add(sample.model.Lobby.fromJson(obj));
        }

     System.out.println(x.encode());

       Observable.interval(5, TimeUnit.SECONDS)
                .subscribe( e -> {
                    getMsg(getTime(), getId());
                });

    }




    public TextField getSendMsg() {
        return sendMsg;
    }


   public void setText(String text){
       messages.add(new Message(getId(), getUser(), getLobby(), text, getTime()));

   }


    public void sendMessage(ActionEvent actionEvent) {

        appMsg.setText("");

        lobbyId = getId();
        String text = getSendMsg().getText();
        String username = getUser();
        lobbyName = getLobby();
        OffsetDateTime time = getTime();

        if(text.isEmpty()){
            Exception e;
            appMsg.setText("Can't send an empty string");
        }
        else {
            sendMessage(lobbyId, username, lobbyName, text, time);
            sendMsg.setText("");
           setText(text);
        }
    }

    public void onExit(ActionEvent actionEvent) {
        Stage oldStage = (Stage) exitBtn.getScene().getWindow();
        oldStage.close();
        System.exit(0);
    }

    public void refresh(ActionEvent actionEvent) {
        setText(text);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sentMsg.setCellFactory(e ->{
            return new MessageCell();
        });
        sentMsg.setItems(messages);

        userList.setCellFactory(e ->{
            return new UserCell();
        });
        userList.setItems(users);
    }
}
