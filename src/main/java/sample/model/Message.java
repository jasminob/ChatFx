package sample.model;

import io.vertx.core.json.JsonObject;

import java.util.UUID;


public class Message {

    UUID msgId;
    String acc;
    UUID lobbyId;
    String text;

    public static Message fromJson(JsonObject object) {

        UUID lobbyId = UUID.fromString(object.getString("lobby_id"));
        String acc = object.getString("username");
        String text = object.getString("text");

        return new Message(lobbyId, acc,  text);

    }

    public static JsonObject toJson(Message message) {
        return new JsonObject().put("lobby_id", message.getLobbyId().toString())
                .put("username", message.getAcc())
                .put("text", message.getText());
    }

    public Message( UUID lobbyId, String acc, String text) {
        this.lobbyId = lobbyId;
        this.acc = acc;
        this.text = text;
    }

    public String getAcc() {
        return acc;
    }

    public UUID getLobbyId() {
        return lobbyId;
    }

    public String getText() {
        return text;
    }

    public UUID getMsgId() {
        return msgId;
    }
}
