import java.nio.file.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.util.Scanner;


public class ContactApplication {
    private static List<Contact> contacts = new ArrayList<>();
    private static final Path CONTACTS_FILE = Paths.get("data", "contacts.txt");
    public static void main(String[] args) {
        loadContacts();
        runningContact();
    }
    public static void loadContacts() {
        try {
            if (Files.exists(CONTACTS_FILE)) {
                List<String> lines = Files.readAllLines(CONTACTS_FILE);
                for (String line : lines) {
                    String[] parts = line.split("\\|");
                    String name = parts[0].trim();
                    String phoneNumber = parts[0].trim();
                    Contact contact = new Contact(name, phoneNumber);
                    contacts.add(contact);
                }
            } else {
                // Add pre-defined contacts here
                Contact contact1 = new Contact("Khinememe", "1234567890");
                Contact contact2 = new Contact("Kyaw", "9876543210");
                Contact contact3 = new Contact("Emilia", "143567890");
                Contact contact4 = new Contact("Mata", "923456710");
                contacts.add(contact1);
                contacts.add(contact2);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading contacts.");
            e.printStackTrace();
        }

    }
    public static void runningContact() {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        do {
            options();
            switch (choice) {
                case 1: view();
                    break;
                case 2:
                      break;
                case 3:
                    break;

                case 4:

                    break;
                case 5:
                    exit();
                    break;
            }
        } while (choice != 5);

    }
    private static void exit() {
        System.out.println("Exiting the application. Goodbye!");
    }

    public static void view() {
        System.out.println("\nContacts:");
        System.out.println("Name            | Phone number");
        System.out.println("----------------|--------------");
        for (Contact contact : contacts) {
            System.out.printf( contact.getName(), contact.getPhoneNumber());
        }
    }

    public static void options() {
        System.out.println("\nMain Menu:");
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.print("Enter an option (1, 2, 3, 4, or 5): ");
    }
}




//        for (int i = 0; i <  ContactName.size(); i += 1) {
//            for (int j = 0; j <  ContactNum.size(); j += 1)
//                System.out.println(ContactName.get(i) + ": " +  ContactNum.get(j));
//        }
//
//        Files.write(
//                Paths.get("data", "contacts.txt"),
//                Arrays.asList("John"), // list with one item
//                StandardOpenOption.APPEND
//        );
//
//        List<String> lines = Files.readAllLines(Paths.get("data", "contacts.txt"));
//        List<String> newList = new ArrayList<>();
//
//        for (String line : lines) {
//            if (line.equals("Khinememe")) {
//                newList.add("Johnny");
//                continue;
//            }
//            newList.add(line);
//        }
//
//        Files.write(Paths.get("data", "contacts.txt"), newList);
//
//

//    }
//}

