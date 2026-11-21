
import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {

    static Scanner in = new Scanner(System.in);
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {

        boolean done = false;
        String choice;

        do {
            displayList();
            displayMenu();

            choice = getRegExString(
                    in, "Enter your choice",
                    "[AaDdIiPpQq]"
            ).toUpperCase();

            switch (choice) {
                case "A":
                    addItem();
                    break;

                case "D":
                    deleteItem();
                    break;

                case "I":
                    insertItem();
                    break;

                case "P":
                    printList();
                    break;

                case "Q":
                    done = getYNConfirm(
                            in, "Are you sure you want to quit?"
                    );
                    break;
            }

        } while (!done);

        System.out.println("Thank you for using List Maker!");
    }

    // Display menu
    private static void displayMenu() {
        System.out.println("\n===== LIST MAKER MENU =====");
        System.out.println("A - Add an item");
        System.out.println("D - Delete an item");
        System.out.println("I - Insert an item");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit");
        System.out.println("===========================");
    }

    // Display numbered list
    private static void displayList() {
        System.out.println("\n===== CURRENT LIST =====");

        if (list.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
        }

        System.out.println("========================");
    }

    // Add item
    private static void addItem() {
        String item = getNonZeroLenString(
                in, "Enter an item to add"
        );

        list.add(item);
        System.out.println("Item added successfully!");
    }

    // Delete item
    private static void deleteItem() {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        displayList();

        int itemNumber = getRangedInt(
                in, "Enter the item number to delete",
                1, list.size()
        );

        String removed = list.remove(itemNumber - 1);
        System.out.println("Deleted: " + removed);
    }

    // Insert item
    private static void insertItem() {
        displayList();

        String item = getNonZeroLenString(
                in, "Enter an item to insert"
        );

        int position = getRangedInt(
                in, "Enter the position",
                1, list.size() + 1
        );

        list.add(position - 1, item);
        System.out.println("Item inserted successfully!");
    }

    // Print list
    private static void printList() {
        displayList();
    }

    // SafeInput: Get non-empty string
    public static String getNonZeroLenString(
            Scanner pipe, String prompt) {

        String retString = "";

        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();

            if (retString.trim().isEmpty()) {
                System.out.println("You must enter some text!");
            }

        } while (retString.trim().isEmpty());

        return retString;
    }

    // SafeInput: Get valid menu choice
    public static String getRegExString(
            Scanner pipe, String prompt, String regEx) {

        String response;

        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine().trim();

            if (!response.matches(regEx)) {
                System.out.println("Invalid choice. Try again.");
            }

        } while (!response.matches(regEx));

        return response;
    }

    // SafeInput: Get integer within range
    public static int getRangedInt(
            Scanner pipe, String prompt,
            int low, int high) {

        int number = 0;
        boolean valid = false;

        do {
            System.out.print("\n" + prompt +
                    " (" + low + "-" + high + "): ");

            String input = pipe.nextLine();

            try {
                number = Integer.parseInt(input.trim());

                if (number >= low && number <= high) {
                    valid = true;
                } else {
                    System.out.println("Number out of range!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }

        } while (!valid);

        return number;
    }

    // SafeInput: Yes or No confirmation
    public static boolean getYNConfirm(
            Scanner pipe, String prompt) {

        String response;

        do {
            System.out.print("\n" + prompt + " (Y/N): ");
            response = pipe.nextLine().trim().toUpperCase();

            if (!response.equals("Y") &&
                    !response.equals("N")) {
                System.out.println("Please enter Y or N.");
            }

        } while (!response.equals("Y") &&
                !response.equals("N"));

        return response.equals("Y");
    }
}
