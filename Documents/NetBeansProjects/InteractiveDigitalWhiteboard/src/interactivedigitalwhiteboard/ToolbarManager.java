package interactivedigitalwhiteboard;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

public class ToolbarManager {
    private HBox controls;

    public ToolbarManager(CanvasManager canvasManager, MediaManager mediaManager, SessionManager sessionManager, Stage stage) {
                // Color Picker for drawing color
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(e -> {
            canvasManager.setDrawingColor(colorPicker.getValue());
        });

        
        // Clear button for clearing the canvas
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> canvasManager.clearCanvas());

        // Button for adding an image
        Button addImageButton = new Button("Add Image");
        addImageButton.setOnAction(e -> mediaManager.addImage(stage));

        // Button for adding a video
        Button addVideoButton = new Button("Add Video");
        addVideoButton.setOnAction(e -> mediaManager.addVideo(stage));


        // Play music button
        Button playMusicButton = new Button("Play Music");
        playMusicButton.setOnAction(e -> mediaManager.playMusic(stage));

        // Stop music button
        Button stopMusicButton = new Button("Stop Music");
        stopMusicButton.setOnAction(e -> mediaManager.stopMusic());

        // Add text button
        Button addTextButton = new Button("Add Text");
        addTextButton.setOnAction(e -> addTextToCanvas(canvasManager));
        
        
        // Save session button for saving the current session
        Button saveSessionButton = new Button("Save Session");
        saveSessionButton.setOnAction(e -> sessionManager.saveSession(stage));

        //  buttons into the HBox layout
        controls = new HBox(10, colorPicker, clearButton, addImageButton, addVideoButton, playMusicButton, stopMusicButton, addTextButton, saveSessionButton);
        controls.getStyleClass().add("hbox");

    }

    public HBox getControls() {
        return controls;
    }

    //JavaFX color format to a valid CSS format
    private String toCssColor(String fxColor) {
        return fxColor.replace("0x", "#").substring(0, 7); // Converts "0x990000ff" to "#990000"
    }

    // Method to add text to the canvas
    private void addTextToCanvas(CanvasManager canvasManager) {
        // Open a dialog to input the text
        TextInputDialog textDialog = new TextInputDialog();
        textDialog.setTitle("Enter Text");
        textDialog.setHeaderText("Add Text to Canvas");
        textDialog.setContentText("Enter your text:");

        // Get the user input
        Optional<String> result = textDialog.showAndWait();

        // If user entered some text, add it to the canvas
        result.ifPresent(text -> {
            Text newText = new Text(text);
            newText.setFont(Font.font("Arial", 20));  // Set default font size
            newText.setLayoutX(100);  // Default X position
            newText.setLayoutY(100);  // Default Y position

            // dragging of the text
            newText.setOnMouseDragged(e -> {
                newText.setLayoutX(e.getSceneX());
                newText.setLayoutY(e.getSceneY());
            });

            // Add the text to the canvas
            canvasManager.getCanvasPane().getChildren().add(newText);
        });
    }
}
