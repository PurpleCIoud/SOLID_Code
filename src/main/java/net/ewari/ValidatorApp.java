package net.ewari;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * This class is the top level for the entire validation step.
 * Later on I will need to make a file-reading system
 * to store my encrypted passwords and not store them in code plaintext
 * TODO: 11/9/22 make a file system that stores my passwords
 * TODO: 11/9/22 make a new class to deal with fetching and passing passwords
 */
public class ValidatorApp {
    public static ArrayList<User> userList = new ArrayList<>(10);
    private static boolean running = true;

    //Test list to test out a list of User classes
    public static void initialiseTestList() {
        Encryptor encryptor = new Encryptor(Algo.SHA256);
        userList.add(new User("Ilja", encryptor.encrypt("1234")));
        userList.add(new User("Mike", encryptor.encrypt("2345")));
        userList.add(new User("Jake", encryptor.encrypt("3456")));
    }

    public static void main(String[] args) {
        initialiseTestList();
        while (running) {
            runApp();
        }
    }

    public static void exit() {
        running = false;
    }

    public static void retry() {
        System.out.print("Not a valid input, ");
    }

    public static void runApp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to login app, L: login, R: register, X: exit.");
        switch (scanner.nextLine().toLowerCase()) {
            case "l" ->  login();
            case "r" ->  register();
            case "x" ->  exit();
            default  ->  retry();
        }
    }
    // Login method to check users entry to an existing list
    // uses Validator, Encryptor and User classes.
    public static void login() {
        Validator v = new Validator();
        Encryptor e = new Encryptor(Algo.SHA256);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        User test = new User(username, e.encrypt(password));
        if (v.validate(userList,test)) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login Failed");
        }
    }
    // Register method that adds a new user to the list
    public static void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username: ");
        String username = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();
        User test = new User(username,password);
        for (User u:userList) {
            if (u.getUsername().equals(test.getUsername())) {
                System.out.println("User already exists, Y:Try again, N:Return to main");
                if ("Y".equalsIgnoreCase(scanner.next())) {
                    register();
                }
            }
        }
    }
}

