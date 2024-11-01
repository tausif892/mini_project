package myPackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Farmer {
    private String farmerName;
    private String farmerID; 
    private String grievance; 
    static int count = 0;

    private static final Path BASE_DIRECTORY = Paths.get(System.getProperty("user.dir"), "chat");

    void createFile(String fileName) {
        File grievanceFile = new File(BASE_DIRECTORY.toFile(), fileName);
        try {
            grievanceFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Cannot Create " + fileName);
        }
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getFarmerName() {
        return this.farmerName;
    }

    public void setFarmerID(String id) {
        this.farmerID = id;
    }

    public String getFarmerID() {
        return this.farmerID;
    }
    public void register() {
        try {
            FileWriter nameFileWriter = new FileWriter("farmerName.txt", true);
            FileWriter idFileWriter = new FileWriter("farmerID.txt", true);
            nameFileWriter.write(getFarmerName() + "\n");
            idFileWriter.write(getFarmerID() + "\n");
            nameFileWriter.close();
            idFileWriter.close();
            count++;
            File file = new File(BASE_DIRECTORY.toFile(), getFarmerID() + ".txt");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFarmer(String id) {
        File path = new File(BASE_DIRECTORY.toFile(), id + ".txt");
        try {
            File tempName = new File("tempName.txt");
            File tempID = new File("tempID.txt");
            tempID.createNewFile();
            tempName.createNewFile();
            File nameFile = new File("farmerName.txt");
            File idFile = new File("farmerID.txt");
            Scanner nameReader = new Scanner(nameFile);
            Scanner idReader = new Scanner(idFile);

            FileWriter tempNameWriter = new FileWriter("tempName.txt", true);
            FileWriter tempIDWriter = new FileWriter("tempID.txt", true);
            String currentID;
            String currentName;
            while (idReader.hasNextLine()) {
                currentID = idReader.nextLine();
                currentName = nameReader.nextLine();
                if (!currentID.equals(id)) {
                    tempIDWriter.write(currentID + "\n");
                    tempNameWriter.write(currentName + "\n");
                }
            }

            nameReader.close();
            idReader.close();
            tempNameWriter.close();
            tempIDWriter.close();

            nameFile.delete();
            idFile.delete();
            File nameDump = new File("farmerName.txt");
            File idDump = new File("farmerID.txt");
            tempID.renameTo(idDump);
            tempName.renameTo(nameDump);
            path.delete();
            count--;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void putGrievance(String grievance) {
        this.grievance = grievance;
        try {
            FileWriter grievanceFileWriter = new FileWriter("Grievance.txt", true);
            grievanceFileWriter.write(getFarmerID() + " " + this.grievance + "\n");
            grievanceFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAnnouncement() {
        File announcementFile = new File("Announcements.txt");
        try {
            Scanner announcement = new Scanner(announcementFile);
            System.out.println("Announcements:\n");
            String currentAnnouncement;
            while (announcement.hasNextLine()) {
                currentAnnouncement = announcement.nextLine();
                if (currentAnnouncement.equals("")) {
                    System.out.println("No Announcements");
                    return;
                }
                System.out.println(announcement.nextLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            File filePath = new File(BASE_DIRECTORY.toFile(), getFarmerID() + ".txt");
            FileWriter messageWriter = new FileWriter(filePath, true);
            messageWriter.write("Farmer: " + message + "\n");
            messageWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void seeMessage() {
        try {
            File filePath = new File(BASE_DIRECTORY.toFile(), getFarmerID() + ".txt");
            Scanner reader = new Scanner(filePath);
            String currentLine = "";
            while (reader.hasNextLine()) {
                currentLine = reader.nextLine();
            }
            if (currentLine.charAt(0) == 'F') {
                System.out.println("No updates from the government");
            } else {
                System.out.println(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
