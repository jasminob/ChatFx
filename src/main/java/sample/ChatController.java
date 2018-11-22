package sample;

import io.vertx.core.json.JsonObject;
import sample.model.ChatApiClient;
import sample.model.Lobby;
import sample.model.Message;

import java.util.UUID;

public class ChatController {

    ChatApiClient apiClient = new ChatApiClient();

    static JsonObject lobby;



    public void doLogin(String lobbyName, String username) {

     lobby = Lobby.toJson(new Lobby(lobbyName, username));
     apiClient.createLobby(lobby);

    }

    public String getLobby(){
        return lobby.getString("lobbyName");
    }

    public String getUser() {
        return lobby.getString("username");
    }

    public void sendMessage(String lobby, String username, String text) {

        JsonObject object = Message.toJson(new Message(lobby, username, text));
        apiClient.sendMsg(object);
    }
}
