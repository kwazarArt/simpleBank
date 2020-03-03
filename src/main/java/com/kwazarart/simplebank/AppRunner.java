package main.java.com.kwazarart.simplebank;

import main.java.com.kwazarart.simplebank.view.ConsoleHelper;

import java.io.IOException;
import java.text.ParseException;

public class AppRunner {
    public static void main(String[] args) throws IOException, ParseException {

        ConsoleHelper ch = new ConsoleHelper();
        ch.start();
    }
}
