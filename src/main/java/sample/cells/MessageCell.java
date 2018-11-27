package sample.cells;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import sample.model.Message;

import java.io.IOException;

public class MessageCell extends ListCell<Message> {

    SimpleStringProperty body = new SimpleStringProperty("");
    SimpleStringProperty user = new SimpleStringProperty("");

    public String getBody() {
        return body.get();
    }

    public SimpleStringProperty bodyProperty() {
        return body;
    }

    public String getUser() {
        return user.get();
    }

    public SimpleStringProperty userProperty() {
        return user;
    }

    @Override
    protected void updateItem(Message item, boolean empty) {
        super.updateItem(item, empty);

        if(empty) {
            setGraphic(null);
        }
        else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/MessageCell.fxml"));
                loader.setController(this);

                setGraphic(loader.load());

                String colon = ": ";

                user.set(item.getAcc()+ colon);
                body.set(item.getText());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}
