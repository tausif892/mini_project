package myPackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Government {
    String announcement;

    public void viewFarmer(){
        File FarmerName= new File("farmerName.txt");
        File FarmerID= new File("farmerID.txt");
        try{
            Scanner nameReader = new Scanner(FarmerName);
            Scanner IdReader = new Scanner(FarmerID);
            int count = 1;
            while(nameReader.hasNextLine() && IdReader.hasNextLine()){
                System.out.println("\nDetails of Farmer "+count++);
                System.out.println("Name: "+nameReader.nextLine());
                System.out.println("Farmer ID : "+IdReader.nextLine()+"\n");
            }
        }
        catch(FileNotFoundException e){
            System.out.println("\nFile Not Found!");
            return;
        }         
    }

    public void makeAnnouncement(String announcement){
        this.announcement=announcement;
        
        try( FileWriter announce = new FileWriter("Anouncements.txt",true)){
            announce.write(announcement + "\n");
        }catch( IOException e){
            System.out.println(e);
        }
    }

    public void seeGrievence(){
        File complaint = new File("Grivence.txt");
        System.out.println("Grievences list");
        try{
            Scanner s = new Scanner(complaint);
            String currentComplaint;
            while (s.hasNextLine()){
                currentComplaint = s.nextLine();
                if (currentComplaint.equals("")){
                    System.out.println("No more grievances left");
                    return;
                }
                System.out.println(currentComplaint);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void sendMessage(String FarmerID,String message){
            String filepath = "//home//student//230905330//oop_project//mini_project//chat//" + FarmerID + ".txt";
        File file = new File(filepath);
        try{
            if (!file.exists()){
                file.createNewFile();
            }
            else{
                try{
                 FileWriter messageWriter = new FileWriter(filepath,true);
                 messageWriter.write("Government: " + message + "\n");
                 messageWriter.close();
                }catch (IOException e){
                 System.out.println(e);
                }
             }
        }catch (Exception e){
            System.out.println(e);
        }
        
    }
    public void seeMessage(String FarmerID){
        try{
            String filepath = "//home//student//230905330//oop_project//mini_project//chat//" + FarmerID + ".txt";
        File messageFile = new File(filepath);
        Scanner reader = new Scanner(messageFile);
        String currentLine = new String();
        while (reader.hasNextLine()==true){
            currentLine = reader.nextLine();
        }
        if (currentLine.charAt(0)=='G'){
            System.out.println("Farmer " + FarmerID + " has posted nothing as of yet");
        }
        else{
            System.out.println(currentLine);
        }
    }catch (Exception e){
        System.out.println(e);
    }
}
}
