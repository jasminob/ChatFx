package sample.model;

import io.vertx.core.json.JsonObject;

import java.util.UUID;

public class Lobby {

    UUID lobbyId;
    String name;
    String username;

    public static Lobby fromJson(JsonObject object) {

        String name = object.getString("lobbyName");
        String username = object.getString("username");


        return new Lobby( name, username);
    }

    public static JsonObject toJson(Lobby lobby) {
        return new JsonObject()
                .put("lobbyName", lobby.getName())
                .put("username", lobby.getUsername());
    }

    public Lobby( String name, String username) {
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public UUID getLobbyId() {
        return lobbyId;
    }

    public String getUsername() {
        return username;
    }

}

