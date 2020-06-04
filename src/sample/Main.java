package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Image blackElephant = new Image(new FileInputStream("e:\\Users\\asus\\Documents\\As A Student\\Term 2\\Advance Programming\\Chess\\Resources\\bb.png"));

        ImageView blackElephantView = new ImageView(blackElephant);
        Menu menu = new Menu("Menu 2");
        MenuBar menuBar = new MenuBar();
//        menu.setGraphic(new ImageView("/wn.png"));

        menu.setOnShowing(e -> { System.out.println("Showing Menu 1"); });
        menu.setOnShown  (e -> { System.out.println("Shown Menu 1"); });
        menu.setOnHiding (e -> { System.out.println("Hiding Menu 1"); });
        menu.setOnHidden (e -> { System.out.println("Hidden Menu 1"); });
        MenuItem menuItem1 = new MenuItem("Item 1");
        MenuItem menuItem2 = new MenuItem("Item 2");
        menu.getItems().add(menuItem1);
        menu.getItems().add(menuItem2);
        menuBar.getMenus().add(menu);
        VBox vBox = new VBox(menuBar);


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group game = new Group(blackElephantView, vBox);
        Scene gameScene = new Scene(game, 800, 800);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
