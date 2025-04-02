package interactivedigitalwhiteboard;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class InteractiveDigitalWhiteboard extends Application {
    private CanvasManager canvasManager;
    private MediaManager mediaManager;
    private ToolbarManager toolbarManager;
    private SessionManager sessionManager;

    @Override
    public void start(Stage primaryStage) {
        canvasManager = new CanvasManager();
        mediaManager = new MediaManager(canvasManager);
        sessionManager = new SessionManager(canvasManager);
        toolbarManager = new ToolbarManager(canvasManager, mediaManager, sessionManager, primaryStage);

        BorderPane root = new BorderPane();
        root.setTop(toolbarManager.getControls());
        root.setCenter(canvasManager.getCanvasPane());

        Scene scene = new Scene(root, 900, 700);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setTitle("JavaFX Whiteboard with Multimedia and Text");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
