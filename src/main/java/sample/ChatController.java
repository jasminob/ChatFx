package sample;

import io.vertx.core.json.JsonObject;
import sample.model.ChatApiClient;
import sample.model.Lobby;
import sample.model.Message;

import java.util.UUID;

public class ChatController {

    ChatApiClient apiClient = new ChatApiClient();

    static JsonObject lobby;
    static JsonObject createLob;



    public void doLogin(String lobbyName, String username) {

     lobby = Lobby.toJson(new Lobby(lobbyName, username));
     createLob = apiClient.createLobby(lobby);

    }

    public String getLobby(){
        return lobby.getString("lobbyName");
    }

    public String getUser() {
        return lobby.getString("username");
    }

    public UUID getId(){
        return UUID.fromString(createLob.getString("id"));
    }

    public void sendMessage(UUID lobbyId, String username, String text) {

        JsonObject object = Message.toJson(new Message(lobbyId, username, text));
        apiClient.sendMsg(object);
    }
}
