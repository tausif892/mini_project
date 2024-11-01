package myPackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.Scanner;
import java.util.*;


public class Government {
    String announcement;
    double subsidiesForEach = 0;
    int seedForEach = 0;
    int fertilizersForEach = 0;
    int equipmentForEach = 0;
    String RealTimeData = "No Recommendations yet!\n";

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
            String filepath = "chat//" + FarmerID + ".txt";
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
            String filepath = "chat//" + FarmerID + ".txt";
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


    public void updateResources(double subsidies,int seedCount, int fertilizersCount,int equipmentCount){
        int count = 0;
        File farmerIDFile=new File("FarmerID.txt");
        try{
            Scanner farmerIDScanner = new Scanner(farmerIDFile);
            String current ;
            while(farmerIDScanner.hasNextLine()){
                current=farmerIDScanner.nextLine();
                count++;
            }
            farmerIDScanner.close();
            this.subsidiesForEach = subsidies/count;
            this.seedForEach = seedCount/count;
            this.fertilizersForEach = fertilizersCount/count;
            this.equipmentForEach = equipmentCount/count;

        }catch(FileNotFoundException e){

            System.out.println("Unexpected Error has Occured\n");

        }

    }

    public void updateRealTimeData(String WeatherForcast , int CropPrices , String CropReco){
        RealTimeData = "Weather Forecast: "+WeatherForcast+"\nCrop Prices: "+CropPrices+"\nCrop Recommendations: "+CropReco;
    }
}
