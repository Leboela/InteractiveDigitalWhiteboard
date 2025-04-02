package interactivedigitalwhiteboard;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SessionManager {
    private final CanvasManager canvasManager;

    public SessionManager(CanvasManager canvasManager) {
        this.canvasManager = canvasManager;
    }

    public void saveSession(Stage stage) {
        WritableImage snapshot = canvasManager.getCanvasPane().snapshot(null, null);
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
