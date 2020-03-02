package main.java.com.kwazarart.simplebank;

import main.java.com.kwazarart.simplebank.view.ConsoleHelper;

import java.io.IOException;

public class AppRunner {
    public static void main(String[] args) throws IOException {

        ConsoleHelper ch = new ConsoleHelper();
        ch.start();
    }
}
