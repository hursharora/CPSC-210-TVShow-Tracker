package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {

    @FXML Button searchButton;

    public void search(ActionEvent actionEvent) throws IOException {
        String searchQuery = searchButton.getText();

        Image yesImage = new Image("file:data/baseline_playlist_add_white_36dp.png");
        Button yesResponseButton = new Button();
        yesResponseButton.setGraphic(new ImageView(yesImage));
        GridPane.setConstraints(yesResponseButton, 1, 3);

        Stage stage = new Stage();

        Button noResponseButton = getNoButton(stage);
        GridPane root = initializeGrid();
        Text addQuestionText = getQuestionText(root);
        Text showInfoText = getInfoText();
        ImageView imageView = getImageView();

        root.getChildren().addAll(imageView, addQuestionText, showInfoText, yesResponseButton, noResponseButton);

        stage.setTitle("Search Result");
        stage.setResizable(false);
        stage.setScene(new Scene(root, 450, 470));
        stage.show();

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


    private Text getInfoText() {
        Text showInfoText = new Text("Episodes: epiNum\nFirst Aired: airDate\nRating: rating\nTVdbID: id");
        GridPane.setConstraints(showInfoText, 1, 2);
        showInfoText.setFont(new Font(15));
        return showInfoText;
    }

    private Text getQuestionText(GridPane root) {
        Text addQuestionText = new Text("Would you like to add rick and morty to your watch list?");
        addQuestionText.setFont(new Font(20));
        addQuestionText.setWrappingWidth(400);
        GridPane.setConstraints(addQuestionText, 0, 1);
        GridPane.setColumnSpan(addQuestionText, 2);
        GridPane.setMargin(addQuestionText, new Insets(10, 50, 10, 10));
        return addQuestionText;
    }

    private ImageView getImageView() {
        Image image = new Image("https://artworks.thetvdb.com/banners/posters/78107-1.jpg", true);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(200);
        imageView.setSmooth(true);
        imageView.setPreserveRatio(true);
        GridPane.setConstraints(imageView, 0, 2);
        GridPane.setHalignment(imageView, HPos.CENTER);
        return imageView;
    }


    public void onEnter(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            search(null);
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
        root.setGridLinesVisible(true);
        return root;
    }
}
