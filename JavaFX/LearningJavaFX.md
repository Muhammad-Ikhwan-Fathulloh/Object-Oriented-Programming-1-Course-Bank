# Learning Java FX

UserView.java
```java
import java.lang.classfile.Label;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
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
        
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.add(usernameField, 1, 0);
        form.add(passwordField, 1, 1);
        
        // Tambahkan ke Root
        root.getChildren().addAll(form);
        return root;
    }
}
```
