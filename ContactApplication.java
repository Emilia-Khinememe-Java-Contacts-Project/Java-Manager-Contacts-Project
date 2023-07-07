import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;


public class ContactApplication {
    private static List<Contact> contacts = new ArrayList<>();
    private static final Path CONTACTS_FILE = Paths.get("data", "contacts.txt");
    public static void main(String[] args) {
        loadContacts();
        saveContact();
        runningContact();
    }
    private static void loadContacts() {
        try {
            if (Files.exists(CONTACTS_FILE)) {
                List<String> lines = Files.readAllLines(CONTACTS_FILE);
                for (String line : lines) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 2) {
                        String name = parts[0].trim();
                        String phoneNumber = parts[1].trim();
                        String formattedPhoneNumber = formatPhoneNumber(phoneNumber);
                        Contact contact = new Contact(name, formattedPhoneNumber);
                        contacts.add(contact);
                    }
                }
            } else {
                // Adding initial contacts
                Contact contact1 = new Contact("Khinememe", "1234567890");
                Contact contact2 = new Contact("Kyaw", "9876543210");
                Contact contact3 = new Contact("Emilia", "143567890");
                Contact contact4 = new Contact("Mata", "923456710");
                contacts.add(contact1);
                contacts.add(contact2);
                contacts.add(contact3);
                contacts.add(contact4);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading contacts.");
            e.printStackTrace();
        }
    }
    // Bonus part formatting the phone number
    public static String formatPhoneNumber(String phoneNumber) {
        StringBuilder sb = new StringBuilder();

        int digitCount = 0;
        for (char c : phoneNumber.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
                digitCount++;

                if (digitCount == 3 || digitCount == 6) {
                    sb.append("-");
                }
            }
        }

        return sb.toString();
    }


    // saving the changes for both add and delete contact methods
    public static void saveContact() {
        try {
            List<String> lines = new ArrayList<>();
            for (Contact contact : contacts) {
                String line = contact.getName() + " | " + contact.getPhoneNumber();
                lines.add(line);
            }
            Files.write(CONTACTS_FILE, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("An error occurred while saving contacts.");
            e.printStackTrace();
        }
        System.out.println(contacts);
    }
    //    Method for User Options
    public static void options() {
        System.out.println("\nMain Menu:");
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.print("Enter an option (1, 2, 3, 4, or 5): ");
    }
    //    Collection user input
    public static int getChoice(Scanner scanner){
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
    //    Methods for collection user input and run different methods based on input
    public static void runningContact() {
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            options();
            choice = getChoice(scanner);
            switch (choice) {
                case 1:
                    view();
                    break;
                case 2:
                    add(scanner);
                    break;
                case 3:
                    search(scanner);
                    break;

                case 4:
                    delete(scanner);
                    break;
                case 5:
                    exit();
                    break;
            }
        } while (choice != 5);

    }

    // Option-1 viewing all the contacts from txt file
    public static void view() {
        System.out.println("\nContacts:");
        System.out.println("Name            | Phone number");
        System.out.println("----------------|--------------");
        for (Contact contact : contacts) {
            System.out.printf("%-16s | %s%n", contact.getName(), contact.getPhoneNumber());
        }
    }
    //    Option-2 adding the contact name and number
    public static void add(Scanner scanner){
        System.out.print("Enter the name : ");
        String name = scanner.nextLine();
        System.out.print("Enter the number : ");
        String phoneNumber =  formatPhoneNumber(scanner.nextLine());
        Contact contact = new Contact(name, phoneNumber);
        contacts.add(contact);
        System.out.println("Contact added!");
        saveContact();
    }
    //    Option-3 searching the contact by name
    public static void search(Scanner scanner){
        System.out.print("Enter the name to search : ");
        String name = scanner.nextLine();
        boolean found = false;
        for(Contact contact : contacts){
            if (contact.getName().equalsIgnoreCase((name))) {
                System.out.println("Contact Found:");
                System.out.println(contact);
                found = true;
                break;
            }
            else {
                System.out.println("Contact not found!");
            }
        }
    }
    //    Option-4 deleting the contact by name
    public static void delete(Scanner scanner){
        System.out.print("Enter the name to delete : ");
        String name = scanner.nextLine();
        boolean deleted = false;
        Iterator<Contact> iterator= contacts.iterator();
        while(iterator.hasNext()){
            Contact contact = iterator.next();
            if(contact.getName().equalsIgnoreCase(name)){
                iterator.remove();
                deleted = true;
                break;
            }
        }
        if(deleted){
            System.out.println("Contact deleted!");
            saveContact();
        }else{
            System.out.println("Contact not found!");
        }
    }
    //    Option 5- exiting the application
    private static void exit() {
        System.out.println("Exiting the application. Goodbye!");
    }


}
