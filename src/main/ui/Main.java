package ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Content;
import model.ListOfContent;
import model.TVShow;
import model.exceptions.InvalidContentTypeException;

import java.io.IOException;


public class Main extends Application {

    private static ListOfContent listOfContent;
    private static GridPane contentGrid = initializeInnerGrid();
    private static int nextColumn = 0;
    private static int nextRow = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = FXMLLoader.load(getClass().getResource("main_activity.fxml"));

        loadAllShows();

        ScrollPane contentList = new ScrollPane(contentGrid);

        GridPane.setConstraints(contentList, 0, 2);



//        Image testImage1 = new Image("https://artworks.thetvdb.com/banners/posters/275274-1.jpg");
//        ImageView testImageView1 = new ImageView();
//        testImageView1.setImage(testImage1);
//        testImageView1.setFitWidth(140);
//        testImageView1.setPreserveRatio(true);
//
//        Image testImage2 = new Image("https://artworks.thetvdb.com/banners/posters/275274-3.jpg");
//        ImageView testImageView2 = new ImageView();
//        testImageView2.setImage(testImage2);
//        testImageView2.setFitWidth(140);
//        testImageView2.setPreserveRatio(true);
//
//        GridPane.setConstraints(testImageView1, 0, 0);
//        GridPane.setConstraints(testImageView2, 1, 0);
//
//        contentGrid.getChildren().addAll(testImageView1, testImageView2);
        root.getChildren().addAll(contentList);



        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 450, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void loadAllShows() {
        for (Content c : listOfContent) {
            displayShow((TVShow)c);
        }
    }

    public static void main(String[] args) throws IOException, InvalidContentTypeException {
        listOfContent = new ListOfContent();
        listOfContent.load("TVShow");
        launch(args);
    }

    private static GridPane initializeInnerGrid() {
        GridPane root = new GridPane();
        for (int i = 0; i < 3; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(33);
            root.getColumnConstraints().add(columnConstraints);
        }

        root.setGridLinesVisible(true);
        return root;
    }

    public static void addShow(TVShow in) {
        listOfContent.insert(in);
        displayShow(in);
    }

    private static void displayShow(TVShow in) {
        Image showImage = new Image(in.getPosterURL());
        ImageView showImageView = new ImageView();
        showImageView.setPreserveRatio(true);
        showImageView.setImage(showImage);
        showImageView.setFitWidth(140);
        GridPane.setConstraints(showImageView, nextColumn, nextRow);
        nextColumn += 1;
        if (nextColumn == 3) {
            nextColumn = 0;
            nextRow += 1;
        }

        contentGrid.getChildren().add(showImageView);
    }

    @Override
    public void stop() throws IOException, InvalidContentTypeException {
        System.out.println("App is Closing");
        listOfContent.save("TVShow");
    }

}



