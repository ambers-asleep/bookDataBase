package org.example.databasegui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class BookInformationController extends Application {
    @FXML
    Button returnButton;
    @FXML
    Button quitButton;
    @FXML
    Label titleLabel;
    @FXML Label authorLabel;
    @FXML Label publisherLabel;
    @FXML Label yearLabel;
    @FXML Label ratingLabel;
    @FXML
    ImageView bookImageView;
    @FXML
    AnchorPane bookInformationPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    public void initialize() throws SQLException {
        //first select type of search and take the ISBN
        DataHolder dataHolder = DataHolder.getInstance();
        titleLabel.setText(dataHolder.getText());
        //chanbeg labels using SQL information
        //make connection
        //changes title and grabs the information using ISBN
        String databaseURL = "jdbc:mysql://10.213.67.225:3306/BookDatabase";
        Connection dbConnection = DriverManager.getConnection(databaseURL, "student", "programming");
        PreparedStatement sqlStatement = dbConnection.prepareStatement("select title from books where ISBN =  " + dataHolder.getText());
        ResultSet resultSet = sqlStatement.executeQuery();
        resultSet = sqlStatement.executeQuery();
        String imageURL = resultSet.getString();
        while (resultSet.next()) {
            titleLabel.setText(resultSet.getString(1));
            authorLabel.setText(resultSet.getString(2));
            publisherLabel.setText(resultSet.getString(3));
            yearLabel.setText(resultSet.getString(4));
            ratingLabel.setText(resultSet.getString(5));

        }
        //set image
        Image bookImage = new Image(getClass().getResourceAsStream(imageURL));
        bookImageView.setImage(bookImage);


    }

    public void returnButtonHandler(ActionEvent event) throws IOException {

        Button clickedButton = (Button) event.getSource();
        if (clickedButton == returnButton) {


            AnchorPane pane = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            bookInformationPane.getChildren().setAll(pane);

        }
        else{
            System.out.println("Not working");
        }
    }

    public void quitButtonHandler(ActionEvent event) throws IOException {

        Button clickedButton = (Button) event.getSource();
        if (clickedButton == quitButton) {

        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();

        }
        else{
            System.out.println("Not working");
        }
    }
public void setLabel(String string){
        titleLabel.setText(string);

}


}
