package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * main.
 */
public class Main {
    /**
     * main method.
     */
    public static void main(String[] args) throws IOException {
        String input = "文";
        System.out.println(SubStringFinder.find("china.txt", input, StandardCharsets.UTF_8));
    }

}