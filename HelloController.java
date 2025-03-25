package org.example.databasegui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.util.Scanner;

public class HelloController {
    @FXML
    Button listAllButton;
    @FXML
    Button searchButton;
    @FXML
    RadioButton titleRadioButton;
    @FXML
    RadioButton authorRadioButton;
    @FXML
    RadioButton publisherRadioButton;
    @FXML
    TextField searchTextField;
    @FXML
    ListView bookListLabel;
    //book list
    public void bookListButtonHandler(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton == listAllButton) {
            String databaseURL = "jdbc:mysql://10.213.67.225:3306/BookDatabase";

            try {
                Connection dbConnection = DriverManager.getConnection(databaseURL, "student", "programming");

                PreparedStatement sqlStatement = dbConnection.prepareStatement("select * from authors;");

                ResultSet queryResults = sqlStatement.executeQuery();

                int authorID;

                String author_name = "";

                while (queryResults.next()) {
                    authorID = queryResults.getInt(1);
                    author_name = queryResults.getString(2);
                    System.out.printf("%-10s%30s\n", authorID, author_name);

                    bookListLabel.getItems().add(author_name);

                }

            } catch (
                    SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Not working");
        }
    }

    //get the gui to communicate with the next page
    public void searchButtonHandler(ActionEvent event) {

        //radio button finding

        Button clickedButton = (Button) event.getSource();
        if (clickedButton == searchButton) {
            String searchQuery = searchTextField.getText();
            searchQuery = searchQuery.toLowerCase();

        }
        else{
            System.out.println("Not working");
        }
    }


}