package ui;

import database.DBStructure;
import dtos.HockeyClub;
import myLib.UserMethods;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.util.Scanner;

public class MainMenu {
    // Void constructor bc static methods are not allowed 👌
    public MainMenu() {}

    public void instantiateMainMenu() {
        Scanner scanner = new Scanner(System.in);
        int userOption = -1;

        while(userOption != 0) {
            printMenu();
            userOption = scanner.nextInt();

            processOption(userOption);
        }
    }

    private void processOption(int userOption) {
        // ↓↓↓ This class' methods are HIGHLY confusing
        UserMethods userMethods = new UserMethods();

        switch(userOption) {
            case 1 -> {
                Path p = userMethods.fileToRead("Input the path of the JSON you want to read:");
                ObjectMapper objectMapper = new ObjectMapper();
                HockeyClub hc = objectMapper.readValue(p.toFile(), HockeyClub.class);
                hc.getMembers().forEach(m -> System.out.println(m.getFirstName()));
            }
            case 2 -> {
                DBStructure structure = new DBStructure();
                structure.createDatabase("CHRuben");
            }
            case 3 -> {
                DBStructure structure = new DBStructure();
                structure.createTables();
            }
        }
    }

    /*
        TODO: Kill myself
        I'm definitely NOT convinced of this implementation
     */
    private void printMenu() {
        System.out.println("------------------------------");
        System.out.println("1. Read JSON file");
        System.out.println("2. Create database");
        System.out.println("3. Create tables (will override if exists!)");
        System.out.println("0. Exit");
        System.out.println("------------------------------");

        System.out.println("Option:");
    }
}