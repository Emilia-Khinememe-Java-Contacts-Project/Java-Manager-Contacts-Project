
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;


public class Contact {
    public static void main(String[] args) throws IOException {

        String directory = "data";
        String filename = "contacts.txt";

        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);

        if (Files.notExists(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }

        if (!Files.exists(dataFile)) {
            Files.createFile(dataFile);
        }
        List<String> contactName = Arrays.asList("Khinememe", "kyaw", "Emilia", "Mata");
        List<String> contactNum = Arrays.asList("5678997534", "9867354789", "3456578998654", "45678987654");
        String Contact = " ";
        for (int i = 0; i < contactName.size(); i++) {
            String name = contactName.get(i);
            String num = contactNum.get(i);
            Contact += name + " | " + num + "\n";
        }
//        System.out.println(Contact);
        List<String> ContactList = Arrays.asList(Contact);
        Path contactpath = Paths.get("data", "contacts.txt");
        Files.write(contactpath, ContactList);
        ContactList = Files.readAllLines(contactpath);
        for (int i = 0; i < ContactList.size()-1; i += 1) {
            System.out.println((i + 1) + ": " + ContactList.get(i));
        }
        Files.write(
                Paths.get("data", "contacts.txt"),
                Arrays.asList("John"),
                StandardOpenOption.APPEND
        );
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


