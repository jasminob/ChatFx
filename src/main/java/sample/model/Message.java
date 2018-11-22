package sample.model;

import io.vertx.core.json.JsonObject;

import java.util.UUID;


public class Message {

    UUID msgId;
    String acc;
    String lobby;
    String text;

    public static Message fromJson(JsonObject object) {

        String lobby = object.getString("lobbyName");
        String acc = object.getString("username");
        String text = object.getString("text");

        return new Message(lobby, acc,  text);

    }

    public static JsonObject toJson(Message message) {
        return new JsonObject().put("lobbyName", message.getLobby())
                .put("username", message.getAcc())
                .put("text", message.getText());
    }

    public Message(String acc, String lobby, String text) {
        this.acc = acc;
        this.lobby = lobby;
        this.text = text;
    }

    public String getAcc() {
        return acc;
    }

    public String getLobby() {
        return lobby;
    }

    public String getText() {
        return text;
    }

    public UUID getMsgId() {
        return msgId;
    }
}
