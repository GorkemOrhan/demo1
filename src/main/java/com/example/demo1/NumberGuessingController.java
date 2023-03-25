package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Random;

public class NumberGuessingController {
    public TextField txtNumber;
    public Label lblResult;
    public Button btnStart;

    private int rndNum;
    private int attempt;
    private int generateRandomNumber(int max){
        Random rnd = new Random();
        int rndNum = rnd.nextInt(max+1);
        return rndNum;
    }
    public void onStartButtonClick(ActionEvent actionEvent) {
        lblResult.setText("You have started the game, please enter your guess below. Total Attempts: 10");
        rndNum = generateRandomNumber(100);
        btnStart.setDisable(true);
    }

    public void onResetButtonClick(ActionEvent actionEvent) {
        btnStart.setDisable(false);
        attempt= 0;
        rndNum = 0;
        lblResult.setText("");
        txtNumber.setText("");
    }

    public void onCheckButtonClick(ActionEvent actionEvent) {
        attempt+=1;
        if (attempt > 10){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You have no attempts left, good luck for the next time.");
            alert.show();
            return;
        }
        String attemptsLeftMes = "You have "+Integer.toString(10-attempt)+" attempts left.";
        int inputNum = Integer.parseInt(txtNumber.getText());

        if (inputNum == rndNum){
            lblResult.setText("Correct guess.");
        }
        else if (inputNum>rndNum){
            lblResult.setText("Too high, go lower. "+attemptsLeftMes);
        }
        else{
            lblResult.setText("Too low, go higher. "+attemptsLeftMes);
        }
    }
}
