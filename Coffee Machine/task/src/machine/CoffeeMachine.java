package machine;
import java.util.Scanner;

public class CoffeeMachine {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static int WATER = 400;
    private static int MILK = 540;
    private static int BEANS = 120;
    private static int CUPS = 9;
    private static int MONEY = 550;
    private static Boolean COMPONENT_FLAG = true;
    private static Boolean APPLICATION_FLAG = true;

    private static void checkStatus() {
        System.out.println("The coffee machine has:");
        System.out.println(WATER + " of water");
        System.out.println(MILK + " of milk");
        System.out.println(BEANS + " of coffee beans");
        System.out.println(CUPS + " of disposable cups");
        System.out.println(MONEY + " of money");
    }

    private static void selectAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = SCANNER.next();

        switch (action) {
            case "buy":
                selectCoffeeType();
                break;
            case "fill":
                selectFill();
                break;
            case "take":
                selectTake();
                break;
            case "remaining":
                checkStatus();
                break;
            case "exit":
                APPLICATION_FLAG = false;
        }
    }

    private static void selectCoffeeType() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String coffeeType = SCANNER.next();

        switch (coffeeType) {
            case "1":
                checkComponents(250, 0, 16, 4);
                break;
            case "2":
                checkComponents(350, 75, 20, 7);
                break;
            case "3":
                checkComponents(200, 100, 12, 6);
                break;
            case "back":
                selectAction();
        }

        COMPONENT_FLAG = true;
    }

    private static void checkComponents(int water, int milk, int beans, int money) {
        if (WATER - water < 0) {
            showError("Sorry, not enough water!");
        } else if (MILK - milk < 0) {
            showError("Sorry, not enough milk!");
        } else if (BEANS - beans < 0) {
            showError("Sorry, not enough beans!");
        } else if (CUPS - 1 < 0) {
            showError("Sorry, not enough cups!");
        }

        if (COMPONENT_FLAG) {
            System.out.println("I have enough resources, making you a coffee!");

            WATER -= water;
            MILK -= milk;
            BEANS -= beans;
            MONEY += money;
            CUPS--;
        }
    }

    private static void showError(String message) {
        System.out.println(message);
        COMPONENT_FLAG = false;
    }

    private static void selectFill() {
        System.out.println("Write how many ml of water do you want to add:");
        WATER += SCANNER.nextInt();

        System.out.println("Write how many ml of milk do you want to add:");
        MILK += SCANNER.nextInt();

        System.out.println("Write how many grams of coffee beans do you want to add:");
        BEANS += SCANNER.nextInt();

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        CUPS += SCANNER.nextInt();
    }

    private static void selectTake() {
        System.out.println("I gave you $" + MONEY);
        MONEY = 0;
    }

    public static void main(String[] args) {
        while (APPLICATION_FLAG) {
            selectAction();
        }
    }
}