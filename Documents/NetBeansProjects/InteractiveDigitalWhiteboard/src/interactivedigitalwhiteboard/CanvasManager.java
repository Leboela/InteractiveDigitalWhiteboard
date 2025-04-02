package interactivedigitalwhiteboard;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class CanvasManager {
    private Canvas canvas;
    private GraphicsContext gc;
    private Pane canvasPane;
    private double lastX, lastY;
    private ArrayList<Text> textNodes = new ArrayList<>();
    private TextField textInputField;
    private Slider lineWidthSlider;
    private Line resizeLine;

    public CanvasManager() {
        canvasPane = new Pane();
        canvas = new Canvas(800, 600); // Default size
        gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK); // Default drawing color
        gc.setLineWidth(2);

        canvasPane.getChildren().add(canvas);

        // mouse event listeners for drawing
        canvas.setOnMousePressed(e -> startDrawing(e.getX(), e.getY()));
        canvas.setOnMouseDragged(e -> draw(e.getX(), e.getY()));

        // a slider for line width adjustment
        lineWidthSlider = new Slider(1, 20, 2); // Min value: 1, Max value: 20, Default value: 2
        lineWidthSlider.setLayoutX(10);
        lineWidthSlider.setLayoutY(620);
        lineWidthSlider.setBlockIncrement(1); // Adjust step size for slider
        lineWidthSlider.setShowTickMarks(true);
        lineWidthSlider.setShowTickLabels(true);

        //  Change line width dynamically
        lineWidthSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            setLineWidth(newValue.doubleValue());
        });

        canvasPane.getChildren().add(lineWidthSlider);

        //resizable line with visible color
        addResizableLine();
    }

    private void addResizableLine() {
        resizeLine = new Line(0, 600, 800, 600); // Positioned at bottom of canvas
        resizeLine.setStroke(Color.RED); // 🔴 Change color to RED for visibility
        resizeLine.setStrokeWidth(5); // ✅ Increase thickness for better visibility

        //resize line draggable
        resizeLine.setOnMouseDragged(e -> {
            double newHeight = e.getY();
            if (newHeight > 200 && newHeight < 900) { // Restrict resizing range
                resizeLine.setStartY(newHeight);
                resizeLine.setEndY(newHeight);
                resizeCanvas(newHeight);
            }
        });

        canvasPane.getChildren().add(resizeLine);
    }

    private void resizeCanvas(double newHeight) {
        canvas.setHeight(newHeight);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Redraw area
    }

    public void setDrawingColor(Color color) {
        gc.setStroke(color); // Updates stroke color
    }

    public Pane getCanvasPane() {
        return canvasPane;
    }

    private void startDrawing(double x, double y) {
        lastX = x;
        lastY = y;
        gc.beginPath(); // Start a new patt
        gc.moveTo(x, y);
    }

    private void draw(double x, double y) {
        gc.lineTo(x, y);
        gc.stroke(); // lines drawn continuously
        lastX = x;
        lastY = y;
    }

    public void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvasPane.getChildren().removeIf(node -> node instanceof Text);
        textNodes.clear();
    }

    public void showTextInputField() {
        textInputField = new TextField();
        textInputField.setPromptText("Enter text...");
        textInputField.setLayoutX(350);
        textInputField.setLayoutY(100);

        textInputField.setOnAction(e -> {
            addText(textInputField.getText());
            canvasPane.getChildren().remove(textInputField);
        });

        canvasPane.getChildren().add(textInputField);
    }

    private void addText(String text) {
        Text textNode = new Text(lastX, lastY, text);
        textNode.setFont(new Font(20));
        textNode.setFill(gc.getStroke());

        textNode.setOnMousePressed(e -> {
            lastX = e.getSceneX() - textNode.getX();
            lastY = e.getSceneY() - textNode.getY();
        });

        textNode.setOnMouseDragged(e -> {
            textNode.setX(e.getSceneX() - lastX);
            textNode.setY(e.getSceneY() - lastY);
        });

        textNodes.add(textNode);
        canvasPane.getChildren().add(textNode);
    }

    public void setLineWidth(double width) {
        gc.setLineWidth(width); // Updates the stroke width
    }
}
