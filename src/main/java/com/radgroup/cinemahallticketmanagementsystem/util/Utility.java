package com.radgroup.cinemahallticketmanagementsystem.util;

import com.radgroup.cinemahallticketmanagementsystem.controllers.Cont;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Utility {
    /**
     * Changes the current scene of the application.
     * This method is intended to be called within a controller method that is connected to a UI node.
     * The UI node should trigger an action event, which is then passed to this method to facilitate
     * the scene transition.
     * @param event Action event of the UI node
     * @param sceneName Name (without extension) of the fxml file that should be displayed. Ex: "Home". This fxml file should be in "/com/radgroup/cinemahallticketmanagementsystem/"
     * @param data Any data that should be dynamically displayed in the view. null if there is no data to pass.
     * @throws IOException Should be handled in the controller method using a try catch block
     */
    public static void switchScene(ActionEvent event, String sceneName, Object data) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Utility.class.getResource("/com/radgroup/cinemahallticketmanagementsystem/"+sceneName+".fxml"));
        Scene Scene = new Scene(fxmlLoader.load());

        // Get the controller associated with the FXML
        Object controller = fxmlLoader.getController();

        // Check if the controller has a method to receive the data
        if (controller instanceof Cont) {
            ((Cont) controller).setView(data); // Pass the data to the controller
        }

        // Get the current stage and set the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(Scene);
        stage.setTitle(sceneName);
        stage.show();
    }

    public static File selectImage(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",  "*.jpg"));

        // Open the file chooser and get the selected file
        return fileChooser.showOpenDialog(stage);
    }

    public static Image loadImage(String imageName, String folder) {
        // Define the folder where images are stored
        String imageFolderPath = System.getProperty("user.dir") + "/images/" + folder;

        // Create the image file path
        File imageFile = new File(imageFolderPath + "/" + imageName + ".jpg");

        // If the image exists, load it, otherwise load a default image
        if (imageFile.exists()) {
            return new Image(imageFile.toURI().toString());
        } else {
            // Load a default image if the movie image does not exist
            File defaultImage = new File(imageFolderPath + "/default.png");
            return new Image(defaultImage.toURI().toString());
        }
    }

    public static void SaveImage(String imageName, File imagefile, String saveLocation) {
        String targetDirectory = System.getProperty("user.dir") + "/images/" + saveLocation;
        File targetDir = new File(targetDirectory);

        // If the directory doesn't exist, create it
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        // Define the new file name
        String newFileName = imageName + ".jpg";
        File targetFile = new File(targetDir, newFileName);

        try {
            // Copy the selected file to the new location with the new name
            Path sourcePath = imagefile.toPath();
            Path targetPath = targetFile.toPath();
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("File copied to: " + targetFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error if file copy fails
        }
    }

    public static void deleteImage(String imageName, String folder) {
        // Define the folder where images are stored
        String imageFolderPath = System.getProperty("user.dir") + "/images/" + folder;  // Project root + 'images' folder

        // Build the path for the specific image
        Path ImagePath = Paths.get(imageFolderPath, imageName + ".jpg");

        // Create a File object for the image
        File movieImageFile = ImagePath.toFile();

        // Check if the file exists
        if (movieImageFile.exists())
            movieImageFile.delete();
    }
}
