/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Heythem
 */
public class Santi extends Application {

    private Stage stage;
    private static Santi instance;
    private Scene scene;

    public Santi() throws IOException, InterruptedException {
        instance = this;
        scene = new Scene(FXMLLoader.load(getClass().getResource("Choixrdv.fxml")));

    }

    public static Santi getInstance() {
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("Recherche.fxml"));

        this.stage = stage;
        stage.setScene(this.scene);
        //stage.setFullScreen(true);
        //stage.setMaximized(true);
        stage.setWidth(1000);
        stage.setHeight(650);
        stage.centerOnScreen();

        stage.show();
    }

    public void changescene(Scene scene) {
        this.scene = scene;
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
