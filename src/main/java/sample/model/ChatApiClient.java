package sample.model;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.buffer.Buffer;
import io.vertx.reactivex.ext.web.client.HttpRequest;
import io.vertx.reactivex.ext.web.client.WebClient;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ChatApiClient {
    private final static String HOST = "http://localhost:8080";
    private final static String URL_CREATE_LOBBY = HOST + "/createLobby";
    private final static String URL_CREATE_ACCOUNT = HOST + "/createAccount";
    private final static String URL_CREATE_MESSAGE = HOST + "/createNewMessage";
    private final static String URL_GET_MESSAGES = HOST + "/getMessages/";
    private final static String URL_GET_USERS = HOST + "/getUsersByLobbyId/";

    private final static String URL_GET_MSG = HOST + "/getMsg/";

    WebClient client;
    Vertx vertx = Vertx.vertx();

    JsonObject login;

    public ChatApiClient() {
        client = WebClient.create(vertx);

    }

    public JsonObject createAccount(JsonObject object) {
        return post(
                URL_CREATE_ACCOUNT,
                object
        );
    }

    public JsonObject createLobby(JsonObject object) {
        return post(
                URL_CREATE_LOBBY,
                object
        );
    }


    public JsonObject sendMsg(JsonObject object) {
        return post(
                URL_CREATE_MESSAGE,
                object
        );
    }

    public JsonArray getMsgs(UUID id, int page, int pageSize) {

        return getArray(URL_GET_MESSAGES + id.toString()
                + "/" + page
                + "/" + pageSize);
    }

    public JsonArray getUsers(UUID id) {

        return getArray(URL_GET_USERS + id.toString());
    }

    public JsonArray getMsg(OffsetDateTime time, UUID lobbyId) {
        return getArray(URL_GET_MSG + time.toString()
                + "/" + lobbyId.toString());
    }


    private JsonObject post(String url) {
        return post(url, null);
    }


    private JsonObject post(String url, JsonObject body) {
        HttpRequest<Buffer> request = client.postAbs(url).putHeader("Content-Type", "application/json");

        if (body == null)
            return request.rxSend().blockingGet().bodyAsJsonObject();
        else {
            return request.rxSendJson(body).blockingGet().bodyAsJsonObject();
        }
    }

    private JsonArray getArray(String url) {
        return client.getAbs(url).rxSend().blockingGet().bodyAsJsonArray();
    }

    private JsonObject get(String url) {
        return client.getAbs(url).rxSend().blockingGet().bodyAsJsonObject();
    }
}
