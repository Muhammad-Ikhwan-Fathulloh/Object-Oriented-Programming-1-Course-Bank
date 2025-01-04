# Learning Java FX

UserView.java
```java
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author DELL-STTB-071
 */
public class UserView {
    public VBox getView() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        TextField passwordField = new TextField();
        passwordField.setPromptText("Password");
        
        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println(usernameField.getText());
                System.out.println(passwordField.getText());
                
                // Inisialisasi Root Layout
                BorderPane root;
                root = new BorderPane();
                
                // Tambahkan Tampilan TodoView
                UserView userView = new UserView();
                root.setCenter(userView.getView());
                
                // Buat Scene
                Scene scene = new Scene(root, 600, 600);
            }
        });
        
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.add(usernameField, 1, 0);
        form.add(passwordField, 1, 1);
        form.add(loginButton, 0, 2);
        
        // Tambahkan ke Root
        root.getChildren().addAll(form);
        return root;
    }
}
```

```HomeView
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author DELL-STTB-071
 */
public class HomeView {
     public VBox getView() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        
        // Tambahkan ke Root
        root.getChildren().addAll(new Text("Hello World"));
        return root;
    }
}
```

App.java
```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Inisialisasi Root Layout
            BorderPane root = new BorderPane();

            // Tambahkan Tampilan TodoView
            UserView userView = new UserView();
            root.setCenter(userView.getView());

            // Buat Scene
            Scene scene = new Scene(root, 600, 600);

            // Atur Stage
            primaryStage.setTitle("User");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
```
