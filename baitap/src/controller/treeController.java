package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class treeController implements Initializable {
    
    @FXML
    public TreeView<File> treeView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Tạo gốc của cây thư mục
        File rootDirectory = new File("C:\\Users\\ASUS\\eclipse-workspace\\baitap\\src\\test"); // Thay đổi đường dẫn với thư mục gốc mong muốn
        TreeItem<File> rootItem = new TreeItem<>(rootDirectory);
        rootItem.setGraphic(getIconForFile(rootDirectory));
        createTreeItems(rootItem, rootDirectory);

        // Thiết lập gốc cho TreeView
        treeView.setRoot(rootItem);
    }

    public void createTreeItems(TreeItem<File> parentItem, File parentFile) {
        File[] files = parentFile.listFiles();
        if (files != null) {
            Arrays.stream(files)
                    .forEach(file -> {
                        TreeItem<File> newItem = new TreeItem<>(file);
                        newItem.setGraphic(getIconForFile(file));
                        newItem.setValue(file); 
                        parentItem.getChildren().add(newItem);
                        if (file.isDirectory()) {
                            createTreeItems(newItem, file);
                        }
                    });
        }
    }

    public ImageView getIconForFile(File file) {
        ImageView imageView ;
        if (file.isDirectory()) {
        	imageView = new ImageView(new Image(getClass().getResourceAsStream("/image/folder.png")));
        }
         else {
            imageView = new ImageView(new Image(getClass().getResourceAsStream("/image/file.png")));
        }
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);
        return imageView;
    }

    @FXML
    public void handleTreeItemClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            TreeItem<File> selectedItem = treeView.getSelectionModel().getSelectedItem();
            if (selectedItem != null && selectedItem.getValue() != null) {
                File selectedFile = selectedItem.getValue();
                if (selectedFile.isFile()) {
                    System.out.println("Clicked");
                    openFile(selectedFile);
                }
            }
        }
    }
    public void openFile(File file) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/text.fxml"));
            AnchorPane textPane = loader.load();

            // Lấy controller từ FXMLLoader
            textController textController = loader.getController();

            textController.displayFile(file);

            Stage stage = new Stage();
            Scene scene = new Scene(textPane);
            stage.setScene(scene);
            stage.setTitle("Text Viewer");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
          
        }
    }

}
