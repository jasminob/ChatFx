package sample.cells;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import sample.model.Lobby;

import java.io.IOException;

public class UserCell extends ListCell<Lobby> {

    SimpleStringProperty user = new SimpleStringProperty("");


    public String getUser() {
        return user.get();
    }

    public SimpleStringProperty userProperty() {
        return user;
    }

    @Override
    protected void updateItem(Lobby item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserCell.fxml"));
                loader.setController(this);
                setGraphic(loader.load());

                user.set(item.getUsername());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
