package myPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Government {
    private String announcement;

    private static final Path BASE_DIRECTORY = Paths.get(System.getProperty("user.dir"), "chat");

    public void viewFarmer() {
        File farmerNameFile = new File("farmerName.txt");
        File farmerIDFile = new File("farmerID.txt");
        try {
            Scanner nameReader = new Scanner(farmerNameFile);
            Scanner idReader = new Scanner(farmerIDFile);
            int count = 1;
            while (nameReader.hasNextLine() && idReader.hasNextLine()) {
                System.out.println("\nDetails of Farmer " + count++);
                System.out.println("Name: " + nameReader.nextLine());
                System.out.println("Farmer ID: " + idReader.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("\nFile Not Found!");
        }
    }

    public void makeAnnouncement(String announcement) {
        this.announcement = announcement;

        try (FileWriter announceWriter = new FileWriter("Announcements.txt", true)) {
            announceWriter.write(announcement + "\n");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void seeGrievance() {
        File grievanceFile = new File("Grievance.txt");
        System.out.println("Grievances list");
        try {
            Scanner scanner = new Scanner(grievanceFile);
            while (scanner.hasNextLine()) {
                String currentGrievance = scanner.nextLine();
                if (currentGrievance.equals("")) {
                    System.out.println("No more grievances left");
                    return;
                }
                System.out.println(currentGrievance);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\nFile Not Found!");
        }
    }

    public void sendMessage(String farmerID, String message) {
        String filePath = Paths.get(BASE_DIRECTORY.toString(), farmerID + ".txt").toString();
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (FileWriter messageWriter = new FileWriter(filePath, true)) {
                messageWriter.write("Government: " + message + "\n");
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void seeMessage(String farmerID) {
        String filePath = Paths.get(BASE_DIRECTORY.toString(), farmerID + ".txt").toString();
        File messageFile = new File(filePath);
        try {
            Scanner reader = new Scanner(messageFile);
            String currentLine = "";
            while (reader.hasNextLine()) {
                currentLine = reader.nextLine();
            }
            if (!currentLine.isEmpty() && currentLine.charAt(0) == 'G') {
                System.out.println("Farmer " + farmerID + " has posted nothing as of yet");
            } else {
                System.out.println(currentLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\nFile Not Found!");
        }
    }
}
