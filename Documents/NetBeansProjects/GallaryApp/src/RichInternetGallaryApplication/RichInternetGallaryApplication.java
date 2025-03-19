/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package RichInternetGallaryApplication;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;
import javafx.scene.transform.Rotate;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;



/**
 *
 * @author HP
 */
public class RichInternetGallaryApplication extends Application {
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 10;
    private static final int THUMBNAIL_SIZE = 100;

    private int currentIndex = 0; // Track current image index
    private Stage imageStage; // Full-image window
    private ImageView fullImageView; // Image display
        // Array of image file names (inside src/images/)
     private final String[] imageUrls = {
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\1.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\2.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\3.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\4.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\5.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\6.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\7.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\8.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\9.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\10.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\11.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\12.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\13.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\14.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\15.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\16.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\17.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\18.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\19.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\20.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\21.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\22.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\23.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\24.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\25.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\26.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\27.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\28.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\29.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\30.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\31.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\32.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\33.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\34.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\35.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\36.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\37.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\38.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\39.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\40.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\41.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\42.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\43.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\44.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\45.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\46.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\47.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\48.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\49.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\50.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\51.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\52.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\53.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\54.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\55.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\56.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\57.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\58.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\59.JPG",
        "file:///C:\\Users\\HP\\Documents\\NetBeansProjects\\GallaryApp\\src\\images\\60.JPG",

           

    };

    
    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.getStyleClass().add("gridPane");
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-padding: 15px; -fx-hgap: 15px; -fx-vgap: 15px;");

        ScrollPane scrollPane=new ScrollPane(gridPane);
        scrollPane.getStyleClass().add("scrollPane"); 
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        int index = 0;
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                if (index < imageUrls.length) {
                    Image image = new Image(imageUrls[index]);

                    // Thumbnail ImageView
                    ImageView thumbnailView = new ImageView(image);
                    
                    thumbnailView.setFitWidth(THUMBNAIL_SIZE);
                    thumbnailView.setFitHeight(THUMBNAIL_SIZE);
                    thumbnailView.setPreserveRatio(true); 
                    // Apply CSS class 
                    thumbnailView.getStyleClass().add("thumbnail");
                    // Apply default style

                   
            // Flip Animation
            RotateTransition flipAnimation = new RotateTransition(Duration.millis(500), thumbnailView);
            flipAnimation.setAxis(Rotate.Y_AXIS);
            flipAnimation.setFromAngle(0);
            flipAnimation.setToAngle(180);
            flipAnimation.setCycleCount(1);
            flipAnimation.setAutoReverse(true);

            // Zoom In Animation
            ScaleTransition zoomIn = new ScaleTransition(Duration.millis(200), thumbnailView);
            zoomIn.setToX(1.2); // 120% size
            zoomIn.setToY(1.2);

            // Zoom Out Animation
            ScaleTransition zoomOut = new ScaleTransition(Duration.millis(200), thumbnailView);
            zoomOut.setToX(1); // Back to normal
            zoomOut.setToY(1);

            // Add hover effects
            thumbnailView.setOnMouseEntered(e -> {
                flipAnimation.stop();
                flipAnimation.setFromAngle(0);
                flipAnimation.setToAngle(180);
                flipAnimation.play();
                zoomIn.play();
            });

            thumbnailView.setOnMouseExited(e -> zoomOut.play());

                    
                    

                    // Click event to show full image
                    int finalIndex = index;
                    thumbnailView.setOnMouseClicked(e -> showFullImage(finalIndex));
 
                    gridPane.add(thumbnailView, col, row);
                    index++;
                }
            }
        }

        Scene scene = new Scene(scrollPane, 800, 600);
        scene.getStylesheets().add(getClass().getResource("MyCSS.css").toExternalForm());
        URL cssURL = getClass().getResource("MyCSS.css");
        System.out.println("CSS File URL: " + cssURL);
        scene.getStylesheets().add(cssURL.toExternalForm());

        primaryStage.setTitle("Rich Internet Gally Application");
        
       

        primaryStage.setScene(scene);
        primaryStage.show();

        
      

       
      
    }

    // Show full-size image with navigation and close button
    private void showFullImage(int index) {
        currentIndex = index;

        if (imageStage == null) {
            imageStage = new Stage();
            imageStage.initModality(Modality.APPLICATION_MODAL);
            imageStage.setTitle("Full-Size Image");

            fullImageView = new ImageView();
            fullImageView.setPreserveRatio(true);
            fullImageView.setFitWidth(600);
            fullImageView.setFitHeight(500);
            
         


        // Load Images
        updateImage();
        
         // Thumbnail row
        HBox thumbnailRow = new HBox(5);
        thumbnailRow.setPadding(new Insets(10));
        
        int maxThumbnails = 30;
        

        for (int i = 0;i < Math.min(imageUrls.length, maxThumbnails); i++) {
            final int thumbIndex = i; // **Make index final**
            Image thumbImage = new Image(imageUrls[i]);
            ImageView thumbView = new ImageView(thumbImage);
            thumbView.setFitWidth(25);
            thumbView.setFitHeight(25);
            thumbView.setPreserveRatio(true);
            
            // Change main image on click
            thumbView.setOnMouseClicked(e -> {
                currentIndex = thumbIndex;
                updateImage();
            });

            thumbnailRow.getChildren().add(thumbView);
        }
        

            

            // Navigation Buttons
            Button prevButton = new Button("◀ Previous"); 
            Button nextButton = new Button("Next ▶");
            Button closeButton = new Button("✖ Close");
            

  

            prevButton.setOnAction(e -> navigate(-1));
            nextButton.setOnAction(e -> navigate(1));
            closeButton.setOnAction(e -> imageStage.close()); // Closes the full-image view
            
            //styling button useng inline
           prevButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-radius: 5px;");
           nextButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-radius: 5px;");
           closeButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-radius: 5px;");

          prevButton.setOnMouseEntered(e -> prevButton.setStyle("-fx-background-color: #0056b3; -fx-text-fill: white;"));
          prevButton.setOnMouseExited(e -> prevButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;"));

          nextButton.setOnMouseEntered(e -> nextButton.setStyle("-fx-background-color: #0056b3; -fx-text-fill: white;"));
          nextButton.setOnMouseExited(e -> nextButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;"));

          closeButton.setOnMouseEntered(e -> closeButton.setStyle("-fx-background-color: #a71d2a; -fx-text-fill: white;"));
          closeButton.setOnMouseExited(e -> closeButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;"));



            HBox buttonBox = new HBox(20, prevButton, nextButton, closeButton);
            buttonBox.setPadding(new Insets(15));
           // buttonBox.setStyle("-fx-alignment: center; -fx-spacing: 15px; -fx-padding: 10px;");
            buttonBox.setAlignment(Pos.CENTER);
            
             
            
            
                // Bottom Container with Thumbnails and Buttons
                VBox bottomContainer = new VBox(15, thumbnailRow, buttonBox);
                bottomContainer.setPadding(new Insets(20));
                bottomContainer.setAlignment(Pos.CENTER); // Center align contents
                bottomContainer.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 15px;");
                
                BorderPane root = new BorderPane();
                root.setCenter(fullImageView);
                root.setBottom(bottomContainer);

                


           

        Scene scene = new Scene(root, 800, 700);
        imageStage.setScene(scene);

           
        }

        updateImage();
        imageStage.show();
    }

    // Navigate through images
    private void navigate(int direction) {
        currentIndex += direction;
        if (currentIndex < 0) {
            currentIndex = imageUrls.length - 1; // Loop back to last image
        } else if (currentIndex >= imageUrls.length) {
            currentIndex = 0; // Loop back to first image
        }
        updateImage();
    }

    // Update the displayed full-size image
    private void updateImage() {
        fullImageView.setImage(new Image(imageUrls[currentIndex]));
    }



    /**
     * @param args the command line arguments
     */
 
    public static void main(String[] args) {
        launch(args);
    }
    
}
