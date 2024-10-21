package myPackage;
import myPackage.Farmer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Government extends Farmer{
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
}
