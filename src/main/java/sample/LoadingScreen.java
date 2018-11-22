package sample;

import io.reactivex.Observable;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class LoadingScreen {
    public ProgressIndicator progressIndicator;
    public BorderPane loadingBorderPane;

    public LoadingScreen() throws IOException {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(6)
                .map(time -> {
                    Platform.runLater(() -> {
                        progressIndicator.setProgress(progressIndicator.getProgress() + 0.2);

                        if (progressIndicator.getProgress() > 1) {


                            Stage stage = new Stage();
                            Parent loader = null;

                            Stage oldStage = (Stage) loadingBorderPane.getScene().getWindow();
                            oldStage.close();
                            try {
                                loader = FXMLLoader.load(getClass().getResource("/Lobby.fxml"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            stage.setTitle("Hello World");
                            stage.setScene(new Scene(loader, 400, 400));
                            stage.show();

                        }
                    });
                    return time;
                })
                .subscribe();

    }
}
