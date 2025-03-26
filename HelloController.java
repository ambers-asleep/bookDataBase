package org.example.databasegui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
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
    @FXML
    AnchorPane rootPane;

    //book list
    public void bookListButtonHandler(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton == listAllButton) {
            String databaseURL = "jdbc:mysql://10.213.67.225:3306/BookDatabase";

            try {
                Connection dbConnection = DriverManager.getConnection(databaseURL, "student", "programming");

                PreparedStatement sqlStatement = dbConnection.prepareStatement("select title from books;");

                ResultSet queryResults = sqlStatement.executeQuery();


                String title = "";

                while (queryResults.next()) {
                    title = queryResults.getString(1);
                    System.out.printf(title + "\n");

                    bookListLabel.getItems().add(title);

                }

            } catch (
                    SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Not working");
        }
    }

    public void radioButtonHandler(ActionEvent event) throws SQLException, IOException {
        RadioButton selectedRadioButton = (RadioButton) event.getSource();
        ResultSet queryResults;
        String databaseURL = "jdbc:mysql://10.213.67.225:3306/BookDatabase";
        Connection dbConnection = DriverManager.getConnection(databaseURL, "student", "programming");
        if (selectedRadioButton == titleRadioButton) {
            String searchQuery = searchTextField.getText();
            searchQuery = searchQuery.toLowerCase();
            System.out.println(searchQuery);
            System.out.println("I need the title looked up");

            //if this is selected look through the book table with a dependency on the title
            databaseURL = "jdbc:mysql://10.213.67.225:3306/BookDatabase";
            dbConnection = DriverManager.getConnection(databaseURL, "student", "programming");
            PreparedStatement sqlStatement = dbConnection.prepareStatement("select * from books where title ='" + searchQuery +"';");
            queryResults = sqlStatement.executeQuery();
            String author_name = "";
            while (queryResults.next()) {
                author_name = queryResults.getString(1);
                System.out.printf(author_name + "\n");
                bookListLabel.getItems().clear();
                bookListLabel.getItems().add(author_name);
            }
        } else if (selectedRadioButton == authorRadioButton) {
            String searchQuery = searchTextField.getText();
            searchQuery = searchQuery.toLowerCase();
            System.out.println(searchQuery);
            System.out.println("I need the author looked up");
            //if this is selected look through the book table with a dependency on the author
            databaseURL = "jdbc:mysql://10.213.67.225:3306/BookDatabase";
            dbConnection = DriverManager.getConnection(databaseURL, "student", "programming");
            PreparedStatement sqlStatement = dbConnection.prepareStatement("select title from books where author_name =" + searchQuery);
            queryResults = sqlStatement.executeQuery();
            String author_name = "";
            while (queryResults.next()) {
                author_name = queryResults.getString(1);
                System.out.printf(author_name + "\n");
                bookListLabel.getItems().clear();
                bookListLabel.getItems().add(author_name);
            }
        } else if (selectedRadioButton == publisherRadioButton) {
            String searchQuery = searchTextField.getText();
            searchQuery = searchQuery.toLowerCase();
            System.out.println(searchQuery);
            System.out.println("I need the publisher looked up");
            //if this is selected look through the publisher table and print on the list view
            PreparedStatement sqlStatement = dbConnection.prepareStatement("select title from books where publisherID =" + searchQuery);
            queryResults = sqlStatement.executeQuery();
            String publisher = "";
            while (queryResults.next()) {
                publisher = queryResults.getString(1);
                System.out.printf(publisher + "\n");
                bookListLabel.getItems().add(publisher);
            }
        }

    }

            //making a query statment



        //get the gui to communicate with the next page
        public void searchButtonHandler (ActionEvent event) throws IOException {
        DataHolder dataHolder = DataHolder.getInstance();

            //radio button finding

            Button clickedButton = (Button) event.getSource();
            if (clickedButton == searchButton) {
                String searchQuery = searchTextField.getText();
                searchQuery = searchQuery.toLowerCase();
                dataHolder.setText(searchQuery);

                AnchorPane pane = FXMLLoader.load(getClass().getResource("bookInformation.fxml"));

                rootPane.getChildren().setAll(pane);



            } else {
                System.out.println("Not working");
            }
        }


    }
