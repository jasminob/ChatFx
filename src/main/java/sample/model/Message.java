package sample.model;

import io.vertx.core.json.JsonObject;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class Message {
    UUID msgId;
    String acc;
    String lobby;
    String text;
    OffsetDateTime createdAt;


    public static Message fromJson(JsonObject object) {

        UUID msgId = UUID.fromString(object.getString("id"));
        String acc = object.getString("username");
        String lobby = object.getString("lobbyname");
        String text = object.getString("text");

        OffsetDateTime time = OffsetDateTime.parse(object.getString("created_at"));

        return new Message(msgId, acc, lobby, text, time);

    }

    public static JsonObject toJson(Message message) {
        return new JsonObject().put("id", message.getMsgId().toString())
                .put("username", message.getAcc())
                .put("lobbyname", message.getLobby())
                .put("text", message.getText())
                .put("created_at", message.getCreatedAt().toString());
    }

    public Message(UUID msgId, String acc, String lobby, String text, OffsetDateTime createdAt) {
        this.msgId = msgId;
        this.acc = acc;
        this.lobby = lobby;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Message(UUID msgId, String acc, String lobby, String text){
        this.msgId = msgId;
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
