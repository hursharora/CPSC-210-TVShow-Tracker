package ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Content;
import model.ListOfContent;
import model.Season;
import model.TVShow;
import model.exceptions.InvalidContentTypeException;
import model.exceptions.ShowNotFoundException;

import java.io.IOException;
import java.util.List;


public class Main extends Application {

    private static ListOfContent listOfContent;
    private static GridPane contentGrid;
    private static int nextColumn;
    private static int nextRow;

    private static Stage stage;
    private static Scene homeScene;

    @Override
    //MODIFIES: this
    //EFFECTS: starts the application by calling methods to initialize the scene and displaying the scene
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        initializeHome();
        primaryStage.setTitle("Track My Shows!");
        primaryStage.setScene(homeScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private static void initializeHome() throws IOException {
        contentGrid = initializeInnerGrid();

        GridPane root = FXMLLoader.load(Main.class.getResource("main_activity.fxml"));
        loadAllShows();

        ScrollPane contentList = new ScrollPane(contentGrid);

        GridPane.setConstraints(contentList, 0, 2);

        root.getChildren().addAll(contentList);

        homeScene = new Scene(root, 450, 600);
        homeScene.getStylesheets().add("ui/styles.css");
    }

    private static void loadAllShows() {
        for (Content c : listOfContent) {
            displayShow((TVShow)c);
        }
    }

    public static void main(String[] args) throws IOException, InvalidContentTypeException {
        //TVShowRequester tvShowRequester = new TVShowRequester();
        //System.out.println(tvShowRequester.showEpisodesFromSeason(275274, 1));
        listOfContent = new ListOfContent();
        listOfContent.load("TVShow");
        launch(args);
    }

    private static GridPane initializeInnerGrid() {
        nextColumn = 0;
        nextRow = 0;
        GridPane root = new GridPane();
        root.getStyleClass().add("root");
        for (int i = 0; i < 3; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(33);
            root.getColumnConstraints().add(columnConstraints);
        }

        return root;
    }

    static void addShow(TVShow in) {
        listOfContent.insert(in);
        displayShow(in);
    }

    private static void displayShow(TVShow in) {
        ImageView showImageView = getImageView(in);
        GridPane.setConstraints(showImageView, nextColumn, nextRow);
        nextColumn += 1;
        if (nextColumn == 3) {
            nextColumn = 0;
            nextRow += 1;
        }

        contentGrid.getChildren().add(showImageView);
    }

    private static ImageView getImageView(TVShow in) {
        Image showImage = new Image(in.getPosterURL());
        ImageView showImageView = new ImageView();
        showImageView.setPreserveRatio(true);
        showImageView.setImage(showImage);
        showImageView.setFitWidth(136);
        showImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                if (in.getEpisodes() == null) {
                    in.loadEpisodes();
                }
            } catch (IOException | ShowNotFoundException e) {
                e.printStackTrace();
            }
            displayEpisodeView(in);
            event.consume();
        });
        return showImageView;
    }

    private static void displayEpisodeView(TVShow in) {
        Button backButton = getBackButton();
        Button deleteShowButton = getDeleteShowButton(in);
        Accordion accordion = new Accordion();
        Text showTitle = getShowTitleText(in);
        showTitle.setFont(new Font(20));
        AnchorPane anchorPane = new AnchorPane(backButton, showTitle, deleteShowButton);
        VBox vbox = new VBox(anchorPane, accordion);
        vbox.setSpacing(10);
        vbox.setPrefWidth(432);
        ScrollPane scr = new ScrollPane(vbox);
        for (Season s : in.getListOfSeasons()) {
            accordion.getPanes().add(new TitledPane("Season " + s.getSeasonNumber(), getEpisodes(in, s)));
        }
        Scene episodesScene = new Scene(scr, 450, 600);
        episodesScene.getStylesheets().add("ui/styles.css");
        stage.setScene(episodesScene);

    }

    private static Text getShowTitleText(TVShow in) {
        Text showTitle = new Text(in.getTitle());
        AnchorPane.setLeftAnchor(showTitle, 75.0);
        AnchorPane.setTopAnchor(showTitle, 10.0);
        showTitle.setFill(Color.WHITE);
        return showTitle;
    }

    private static Button getDeleteShowButton(TVShow in) {
        Button button = new Button("Delete Show");
        AnchorPane.setRightAnchor(button, 0.0);
        button.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        button.setOnAction(event -> {
            listOfContent.removeContent(in);
            try {
                initializeHome();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(homeScene);
        });
        return button;
    }

    private static Node getEpisodes(TVShow t, Season s) {
        VBox episodeList = new VBox();
        episodeList.setSpacing(10);
        List<Content> seaEps = t.getEpisodesFromSeason(s);
        for (int i = 0; i < seaEps.size(); i++) {
            AnchorPane anchorPane = new AnchorPane();
            String labelText = i + 1 + ". " + seaEps.get(i).getTitle();
            anchorPane.getChildren().addAll(new Label(labelText), getWatchButton(seaEps.get(i)));
            episodeList.getChildren().add(anchorPane);
        }
        return episodeList;
    }

    private static Node getWatchButton(Content i) {
        Button button = new Button();
        AnchorPane.setRightAnchor(button, 0.0);
        if (i.isWatched()) {
            button.setText("Mark as Unwatched");
        } else {
            button.setText("Mark as Watched");
        }
        button.setOnAction(event -> {
            i.toggleWatched();
            if (i.isWatched()) {
                button.setText("Mark as Unwatched");
            } else {
                button.setText("Mark as Watched");
            }
        });
        return button;
    }

    private static Button getBackButton() {
        Button button = new Button("Back");
        AnchorPane.setLeftAnchor(button, 0.0);
        button.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        button.setOnAction(event -> stage.setScene(homeScene));
        return button;
    }

    @Override
    //EFFECTS: when the application is closed, saves list of content
    public void stop() throws IOException, InvalidContentTypeException {
        System.out.println("App is Closing");
        listOfContent.save("TVShow");
    }




}



