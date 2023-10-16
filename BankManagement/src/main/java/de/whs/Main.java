package de.whs;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {
        ClearScreen();
        System.out.println("BankManagement");

        System.out.println("Enter action");
        System.out.println("Add account (1)");
        System.out.println("Sign in (2)");
        System.out.println("Exit (3)");

        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();

        switch (action) {
            case 1:
                AddAccount();
                main(args);
                break;
            case 2:
                SignIn();
                main(args);
                break;
            case 3:
                System.out.println("Goodbye");
                System.exit(0);
            default:
                throw new InvalidParameterException("Invalid action");
        }
    }

    private static void AddAccount() {
        ClearScreen();
        System.out.println("Enter Password for new account");

        Scanner scanner = new Scanner(System.in);
        String password = scanner.next();

        Account newAccount = new Account();

        newAccount.Id = accounts.size();
        newAccount.Password = password;

        accounts.add(newAccount);
        System.out.println("New accountId: " + newAccount.Id);
    }

    private static void SignIn() {
        ClearScreen();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the account Id");
        int accountId = scanner.nextInt();

        int accountPosition = -1;

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).Id == accountId) {
                accountPosition = i;
            }
        }

        if (accountPosition == -1) {
            throw new InvalidParameterException("Account does not exist");
        }

        System.out.println("Enter the account password");
        String accountPassword = scanner.next();

        if (!accounts.get(accountPosition).Password.equals(accountPassword)) {
            throw new InvalidParameterException("Password incorrect");
        }

        ClearScreen();
        System.out.println("Welcome");
        System.out.println("Account balance: " + accounts.get(accountPosition).Balance);
    }

    private static void ClearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}