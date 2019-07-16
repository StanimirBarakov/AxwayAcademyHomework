package com.academy.axway;

import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String directoryToSearch = scanner.nextLine();
        Distributor distributor = new Distributor();
        try {
            distributor.doLettersDistribution(directoryToSearch);
        } catch (NoSuchFileException e) {
            e.printStackTrace();
        }

    }
}
