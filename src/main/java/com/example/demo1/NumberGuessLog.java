package com.example.demo1;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NumberGuessLog {

    public NumberGuessLog() {
        userGuessList = new ArrayList<UserGuess>();
    }

    public int randomNumber;
    public int totalAttempts;
    public ArrayList<UserGuess> userGuessList;

}
