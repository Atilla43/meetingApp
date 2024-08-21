package com.example.meetingapp;

import com.example.meetingapp.Model.Meeting;
import com.example.meetingapp.service.MeetingService;
import com.example.meetingapp.service.ReminderService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import java.io.IOException;

public class HelloApplication extends Application {
    private final MeetingService meetingService = new MeetingService();
    private final ListView<Meeting> meetingListView = new ListView<>();
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private Label notificationLabel = new Label();
    private final ReminderService reminderService = new ReminderService(notificationLabel);


    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = createGrid();

        addUIControls(gridPane);
        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Управление встречами");

        primaryStage.show();
    }

    private void addUIControls(GridPane gridPane) {

        // Add Header
        Label headerLabel = new Label("Управление встречами");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Название встречи : ");
        gridPane.add(nameLabel, 0,1);

        // Add Name Text Field
        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1,1);


        Label dateTimeStartLabel = new Label("Время начала : ");
        gridPane.add(dateTimeStartLabel, 0, 2);

        TextField dateTimeStartField = new TextField();
        dateTimeStartField.setPrefHeight(40);
        gridPane.add(dateTimeStartField, 1, 2);
        dateTimeStartField.setPromptText("yyyy-MM-dd HH:mm");

        Label dateTimeEndLabel = new Label("Время окончания ");
        gridPane.add(dateTimeEndLabel, 0, 3);

        TextField dateTimeEndField = new TextField();
        dateTimeEndField.setPrefHeight(40);
        gridPane.add(dateTimeEndField, 1, 3);
        dateTimeEndField.setPromptText("yyyy-MM-dd HH:mm");


        Label dateTimeReminderLabel = new Label("Время напоминания ");
        gridPane.add(dateTimeReminderLabel, 0, 4);

        TextField dateTimeReminderField = new TextField();
        dateTimeReminderField.setPrefHeight(40);
        gridPane.add(dateTimeReminderField, 1, 4);
        dateTimeReminderField.setPromptText("yyyy-MM-dd HH:mm");



        // Add Submit Button
        Button submitButton = new Button("Добавить встречу");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(150);
        gridPane.add(submitButton, 0, 5, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        GridPane.setHalignment(notificationLabel, HPos.CENTER);
        notificationLabel.setPrefWidth(600);
        gridPane.add(notificationLabel, 0, 6, 2, 1);


        submitButton.setOnAction(e -> {

                Meeting meeting = new Meeting(
                        nameField.getText(),
                        LocalDateTime.parse(dateTimeStartField.getText(), dtf),
                        LocalDateTime.parse(dateTimeEndField.getText(), dtf),
                        LocalDateTime.parse(dateTimeReminderField.getText(), dtf)
                );
                if (meetingService.addMeeting(meeting)) {
                    meetingListView.getItems().add(meeting);
                    reminderService.scheduleReminder(meeting);
                } else {
                    System.out.println("Встреча пересекается с другой встречей.");
                }

        });
    }

    private GridPane createGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(55);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(100,100, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
        return gridPane;

    }


    public static void main(String[] args) {
        launch();
    }
}