package sample;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import sample.model.ChatApiClient;
import sample.model.Lobby;
import sample.model.Message;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ChatController {

    ChatApiClient apiClient = new ChatApiClient();

    static JsonObject lobby;
    static JsonObject createLob;
    static JsonObject msg;


    public void doLogin(String lobbyName, String username) {

        lobby = Lobby.toJson(new Lobby(lobbyName, username));
        createLob = apiClient.createLobby(lobby);

    }

    public String getLobby() {
        return lobby.getString("lobbyName");
    }

    public String getUser() {
        return lobby.getString("username");
    }

    public UUID getId() {
        return UUID.fromString(createLob.getString("id"));
    }

    public OffsetDateTime getTime() {
        return OffsetDateTime.parse(msg.getString("created_at"));
    }

    public void sendMessage(UUID lobbyId, String username, String lobbyName, String text, OffsetDateTime time) {

        JsonObject object = Message.toJson(new Message(lobbyId, username, lobbyName, text, time));
         apiClient.sendMsg(object);
    }

    public JsonArray getMessages(UUID id, int page, int pageSize) {
        JsonArray objects = apiClient.getMsgs(id, page, pageSize);
        msg = objects.getJsonObject(objects.size()-1);
        return objects;
    }

    public JsonArray getUsers(UUID id) {
        return apiClient.getUsers(id);
    }

    public JsonArray getMsg(OffsetDateTime time, UUID lobbyId) {
        return apiClient.getMsg(time, lobbyId);
    }
}
