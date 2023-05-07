package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class NumberGuessingController {
    public TextField txtNumber;
    public Label lblResult;
    public Button btnStart;

    private int rndNum;
    private int attempt;
    private static int inputNum;
    static String message;
    NumberGuessLog numberGuessLog = new NumberGuessLog();


    private int generateRandomNumber(int max){
        Random rnd = new Random();
        int rndNum = rnd.nextInt(max+1);
        return rndNum;
    }
    public void onStartButtonClick(ActionEvent actionEvent) throws IOException {
        int totalAttempt = 10;
        lblResult.setText("You have started the game, please enter your guess below. Total Attempts: "+totalAttempt);
        rndNum = generateRandomNumber(100);
        try {
            randomNumberWriter(rndNum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        numberGuessLog.randomNumber = rndNum;
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
        inputNum = Integer.parseInt(txtNumber.getText());
        if (inputNum == rndNum){
            message = "Correct guess";
            lblResult.setText(message);
        }
        else if (inputNum>rndNum){
            message = "Too high, go lower.";
            lblResult.setText(message+attemptsLeftMes);
        }
        else{
            message = "Too low, go higher.";
            lblResult.setText(message+attemptsLeftMes);
        }
        UserGuess userGuess = new UserGuess();
        userGuess.enteredNum = inputNum;
        userGuess.result = message;
        numberGuessLog.userGuessList.add(userGuess);
    }

    public static void randomNumberWriter(int rndNum) throws IOException {
        File myFile = new File("rndNum.txt");
        FileWriter myFileWriter = new FileWriter("rndNum.txt");
        myFile.createNewFile();
        try {
            myFileWriter.write("{\n\"randomNumber\": "+Integer.toString(rndNum)+",\n");
            myFileWriter.write("\"totalAttempts\": 10,\n");
            myFileWriter.write("\"userGuessList\": [\n{\"enteredNum\": "+Integer.toString(inputNum)+", \"result\": "+message+"}\n]\n}");
            myFileWriter.close();
        } catch (IOException e) {
            System.out.println("File couldn't be created.");
            throw new RuntimeException(e);
        }

    }
}
