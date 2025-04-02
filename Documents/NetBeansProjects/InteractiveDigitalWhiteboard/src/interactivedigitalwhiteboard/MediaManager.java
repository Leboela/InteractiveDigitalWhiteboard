package interactivedigitalwhiteboard;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class MediaManager {
    private CanvasManager canvasManager;
    private MediaPlayer musicPlayer;

    public MediaManager(CanvasManager canvasManager) {
        this.canvasManager = canvasManager;
    }

    public void addImage(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            imageView.setPreserveRatio(true);
            imageView.setLayoutX(100);
            imageView.setLayoutY(100);

            //Dragging
            imageView.setOnMouseDragged(e -> {
                imageView.setLayoutX(e.getSceneX() - imageView.getFitWidth() / 2);
                imageView.setLayoutY(e.getSceneY() - imageView.getFitHeight() / 2);
            });

            //Resizing with Scroll Wheel
            imageView.setOnScroll((ScrollEvent e) -> {
                double scale = (e.getDeltaY() > 0) ? 1.1 : 0.9;
                imageView.setFitWidth(imageView.getFitWidth() * scale);
                imageView.setFitHeight(imageView.getFitHeight() * scale);
            });

            // Removal on Click
            imageView.setOnMouseClicked(e -> {
                Pane canvasPane = canvasManager.getCanvasPane();
                canvasPane.getChildren().remove(imageView);
            });

            canvasManager.getCanvasPane().getChildren().add(imageView);
        }
    }

    public void addVideo(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Videos", "*.mp4", "*.avi", "*.mov"));
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Media media = new Media(file.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);
            mediaView.setFitWidth(300);
            mediaView.setFitHeight(200);
            mediaView.setLayoutX(100);
            mediaView.setLayoutY(150);

            // Video  Starts After Ready
            mediaPlayer.setOnReady(mediaPlayer::play);

            // Dragging of Video
            mediaView.setOnMouseDragged(e -> {
                mediaView.setLayoutX(e.getSceneX() - mediaView.getFitWidth() / 2);
                mediaView.setLayoutY(e.getSceneY() - mediaView.getFitHeight() / 2);
            });

            //Resizing with Scroll Wheel
            mediaView.setOnScroll((ScrollEvent e) -> {
                double scale = (e.getDeltaY() > 0) ? 1.1 : 0.9;
                mediaView.setFitWidth(mediaView.getFitWidth() * scale);
                mediaView.setFitHeight(mediaView.getFitHeight() * scale);
            });

            // Removal on Click
            mediaView.setOnMouseClicked(e -> {
                mediaPlayer.stop(); // Stop video before removing
                canvasManager.getCanvasPane().getChildren().remove(mediaView);
            });

            canvasManager.getCanvasPane().getChildren().add(mediaView);
        }
    }

    public void playMusic(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav"));
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Media media = new Media(file.toURI().toString());
            musicPlayer = new MediaPlayer(media);
            musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            //  Audio Loads Before Playing
            musicPlayer.setOnReady(() -> musicPlayer.play());
        }
    }

    public void stopMusic() {
        if (musicPlayer != null) {
            musicPlayer.stop();
        }
    }
}
