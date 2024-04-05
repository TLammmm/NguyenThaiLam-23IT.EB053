package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load file FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/directoryTree.fxml"));
        Parent root = loader.load();

        // Lấy controller từ FXMLLoader

        // Thiết lập sự kiện đóng ứng dụng
        primaryStage.setOnCloseRequest(event -> {
            // Thực hiện các thao tác cần thiết trước khi đóng ứng dụng (nếu có)
        });

        // Thiết lập scene và hiển thị stage
        Scene scene = new Scene(root);
        primaryStage.setTitle("TreeView Sample");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}