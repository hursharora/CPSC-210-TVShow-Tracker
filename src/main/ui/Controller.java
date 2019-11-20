package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.TVShow;
import model.exceptions.ShowNotFoundException;
import network.ShowInfoGetter;

import java.io.IOException;


public class Controller {

    @FXML TextField searchTextField;
    @FXML Button searchButton;

    public void search() {
        String searchQuery = searchTextField.getText();
        try {
            TVShow queryResult = searchShow(searchQuery);
            Stage stage = new Stage();

            GridPane root = getGridPane(stage, queryResult);

            stage.setTitle("Search Result");
            stage.setResizable(false);
            stage.setScene(new Scene(root, 450, 470));
            stage.show();
        } catch (IOException | ShowNotFoundException e) {
            e.printStackTrace(); //TODO display window showing error or show not found
        }


    }

    private GridPane getGridPane(Stage stage, TVShow queryResult) {
        Button yesResponseButton = getYesButton(queryResult, stage);
        Button noResponseButton = getNoButton(stage);
        GridPane root = initializeGrid();
        Text addQuestionText = getQuestionText(queryResult);
        Text showInfoText = getInfoText(queryResult);
        ImageView imageView = getImageView(queryResult);

        root.getChildren().addAll(imageView, addQuestionText, showInfoText, yesResponseButton, noResponseButton);
        return root;
    }

    private TVShow searchShow(String searchQuery) throws IOException, ShowNotFoundException {
        ShowInfoGetter showInfoGetter = new ShowInfoGetter(searchQuery);
        return showInfoGetter.getSearchedShow();

    }

    private Button getYesButton(TVShow queryResult, Stage stage) {
        Image yesImage = new Image("file:data/baseline_playlist_add_white_36dp.png");
        Button yesResponseButton = new Button();
        yesResponseButton.setGraphic(new ImageView(yesImage));
        GridPane.setConstraints(yesResponseButton, 1, 3);
        yesResponseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.addShow(queryResult);
                stage.close();
            }
        });
        return yesResponseButton;
    }

    private Button getNoButton(Stage stage) {
        Image noImage = new Image("file:data/baseline_clear_white_36dp.png");
        Button noResponseButton = new Button();
        noResponseButton.setGraphic(new ImageView(noImage));
        GridPane.setConstraints(noResponseButton, 0, 3);
        GridPane.setHalignment(noResponseButton, HPos.RIGHT);
        noResponseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                stage.close();
            }
        });
        return noResponseButton;
    }


    private Text getInfoText(TVShow queryResult) {
        String showInfo = "Episodes: epiNum\nFirst Aired: airDate\nRating: rate\nTVdbID: id";
        showInfo = showInfo.replaceAll("epiNum", Integer.toString(queryResult.getNumEpisodes()));
        showInfo = showInfo.replaceAll("airDate", queryResult.getReleaseDate());
        showInfo = showInfo.replaceAll("rate", Integer.toString(queryResult.getRating()));
        showInfo = showInfo.replaceAll("id", Long.toString(queryResult.getTvdbID()));
        Text showInfoText = new Text(showInfo);
        GridPane.setConstraints(showInfoText, 1, 2);
        showInfoText.setFont(new Font(15));
        return showInfoText;
    }

    private Text getQuestionText(TVShow queryResult) {
        String questionText = "Would you like to add x to your watch list?".replaceAll("x", queryResult.getTitle());
        Text addQuestionText = new Text(questionText);
        addQuestionText.setFont(new Font(20));
        addQuestionText.setWrappingWidth(400);
        GridPane.setConstraints(addQuestionText, 0, 1);
        GridPane.setColumnSpan(addQuestionText, 2);
        GridPane.setMargin(addQuestionText, new Insets(10, 50, 10, 10));
        return addQuestionText;
    }

    private ImageView getImageView(TVShow queryResult) {
        Image image = new Image(queryResult.getPosterURL(), true);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(200);
        imageView.setSmooth(true);
        imageView.setPreserveRatio(true);
        GridPane.setConstraints(imageView, 0, 2);
        GridPane.setHalignment(imageView, HPos.CENTER);
        return imageView;
    }


    public void onEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            search();
        }
    }

    private GridPane initializeGrid() {
        GridPane root = new GridPane();
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        root.getColumnConstraints().addAll(column1, column2);
        root.setHgap(10);
        root.setVgap(10);
        return root;
    }
}
