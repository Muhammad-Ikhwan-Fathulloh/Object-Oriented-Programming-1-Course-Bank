# Menggunakan JavaFX

Berikut adalah langkah-langkah untuk membuat tampilan menggunakan JavaFX. Saya akan memberikan contoh JavaFX.

---

## Jika menggunakan Maven:
- Buat file pom.xml di root proyek dan tambahkan dependensi berikut:

```xml
<dependencies>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21.0.0</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>21.0.0</version>
    </dependency>
</dependencies>
```

Todo.java
```java
import javafx.beans.property.*;

public class Todo {
    private IntegerProperty id;
    private StringProperty title;
    private StringProperty description;
    private BooleanProperty isCompleted;
    private StringProperty createdAt;

    public Todo(int id, String title, String description, boolean isCompleted, String createdAt) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.isCompleted = new SimpleBooleanProperty(isCompleted);
        this.createdAt = new SimpleStringProperty(createdAt);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public boolean isIsCompleted() {
        return isCompleted.get();
    }

    public BooleanProperty isCompletedProperty() {
        return isCompleted;
    }

    public String getCreatedAt() {
        return createdAt.get();
    }

    public StringProperty createdAtProperty() {
        return createdAt;
    }
}
```

TodoView.java
```java
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class TodoView {
    private TodoOperations todoOperations;
    private TableView<Todo> tableView;
    private ObservableList<Todo> todoList;

    public TodoView() {
        try {
            todoOperations = new TodoOperations();
            todoList = FXCollections.observableArrayList(todoOperations.getTodos());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public VBox getView() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        // TableView
        tableView = new TableView<>();
        tableView.setItems(todoList);

        // Kolom
        TableColumn<Todo, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> data.getValue().idProperty().asObject());

        TableColumn<Todo, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(data -> data.getValue().titleProperty());

        TableColumn<Todo, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(data -> data.getValue().descriptionProperty());

        TableColumn<Todo, Boolean> isCompletedColumn = new TableColumn<>("Completed");
        isCompletedColumn.setCellValueFactory(data -> data.getValue().isCompletedProperty());

        tableView.getColumns().addAll(idColumn, titleColumn, descriptionColumn, isCompletedColumn);

        // Form
        TextField titleField = new TextField();
        titleField.setPromptText("Title");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            Todo todo = new Todo(0, titleField.getText(), descriptionField.getText(), false, null);
            todoOperations.addTodo(todo);
            refreshTable();
            titleField.clear();
            descriptionField.clear();
        });

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> {
            Todo selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                todoOperations.updateTodo(selected.getId(), titleField.getText(), descriptionField.getText());
                refreshTable();
            }
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Todo selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                todoOperations.deleteTodo(selected.getId());
                refreshTable();
            }
        });

        Button markCompletedButton = new Button("Mark as Completed");
        markCompletedButton.setOnAction(e -> {
            Todo selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                todoOperations.markAsCompleted(selected.getId());
                refreshTable();
            }
        });

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.add(new Label("Title:"), 0, 0);
        form.add(titleField, 1, 0);
        form.add(new Label("Description:"), 0, 1);
        form.add(descriptionField, 1, 1);
        form.add(addButton, 0, 2);
        form.add(updateButton, 1, 2);
        form.add(deleteButton, 2, 2);
        form.add(markCompletedButton, 3, 2);

        // Tambahkan ke Root
        root.getChildren().addAll(tableView, form);
        return root;
    }

    private void refreshTable() {
        todoList.setAll(todoOperations.getTodos());
    }
}
```

Main.java
```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Inisialisasi Root Layout
            BorderPane root = new BorderPane();

            // Tambahkan Tampilan TodoView
            TodoView todoView = new TodoView();
            root.setCenter(todoView.getView());

            // Buat Scene
            Scene scene = new Scene(root, 800, 600);

            // Atur Stage
            primaryStage.setTitle("To-Do Manager");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```
