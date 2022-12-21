import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewCalculations.fxml"));
    	Parent root = loader.load();

    	Controller controller = loader.getController();

        primaryStage.setTitle("Runway Redeclaration Tool");
        primaryStage.getIcons().add(new Image("pngtree-vector-airplane-icon-png-image_1024816.jpg"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        controller.openHelp(null);
    }
}