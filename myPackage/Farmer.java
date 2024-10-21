package myPackage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Farmer{
    private String farmerName;
    private String FarmerID;
    private String Grivence;
    static int count = 0;

    void createFile(String Fname){
        
        // File farmerNameFile= new File("farmerName.txt");
        // File farmerIDFile= new File("farmerID.txt");
        File grivenceFile= new File(Fname);
        try {
            grivenceFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Cannot Create "+Fname);
        }   

    }
    public void setFarmerName(String farmerName){
        this.farmerName=farmerName;
    }
    String getFarmerName(){
        return this.farmerName;
    }
   public  void setFarmerID(String num){
        this.FarmerID=num;
    }
    String getFarmerID(){
        return this.FarmerID;
    }
    public void Register(){
        try{
            FileWriter NamefileWriter= new FileWriter("farmerName.txt",true);
            FileWriter IDfileWriter= new FileWriter("farmerID.txt",true);
            NamefileWriter.write(getFarmerName()+"\n");
            IDfileWriter.write(getFarmerID()+"\n");
            NamefileWriter.close();
            IDfileWriter.close();
            count++;
            String filepath = "C:\\oop_project\\chat\\" + getFarmerID() + ".txt";
            File file = new File(filepath);
            file.createNewFile();
        }
        catch(IOException e){
            assert  true;
        }
    }

    public void deleteFarmer(String id){
        String path = "C:\\oop_project\\chat\\"+getFarmerID()+".txt";
        try{
            File TempName= new File("tempName.txt");
            File TempID= new File("tempID.txt");
            TempID.createNewFile();
            TempName.createNewFile();
            File NameFile= new File("farmerName.txt");
            File IdFile= new File("farmerID.txt");
            Scanner NameRead=new Scanner(NameFile);
            Scanner IDRead=new Scanner(IdFile);


            FileWriter TempNameWriter=new FileWriter("tempName.txt",true);
            FileWriter TempIDWriter=new FileWriter("tempID.txt",true);
            String CurrentID;
            String CurrentName;
            while(IDRead.hasNextLine()){
                CurrentID=IDRead.nextLine();
                CurrentName=NameRead.nextLine();
                if(!CurrentID.equals(id)){
                    TempIDWriter.write(CurrentID+"\n");
                    TempNameWriter.write(CurrentName+"\n");
                }
            }


            NameRead.close();
            IDRead.close();
            TempNameWriter.close();
            TempIDWriter.close();
            

            NameFile.delete();
            IdFile.delete();
            File NameDump= new File("farmerName.txt");
            File IdDump= new File("farmerID.txt");
            TempID.renameTo(IdDump);
            TempName.renameTo(NameDump);
            File file = new File(path);
            file.delete();
            count--;

        }


        catch(IOException e){
            assert true;
        }
    }

    public void putGrivence(String Grivence){
        this.Grivence=Grivence;
        try{
            FileWriter grivenceFileWriter = new FileWriter("Grivence.txt",true);
            grivenceFileWriter.write(getFarmerID() + " " + this.Grivence+"\n");
            grivenceFileWriter.close();
        }
        catch(IOException e){
            // createFile("Grivence.txt");
            // putGrivence(getFarmerID() +" " +  this.Grivence + "\n");
            System.out.println(e);
        }
    }

    public void getAnouncement(){
        File anouncementFile =new File("Anouncements.txt");
        try {
            Scanner anouncement=new Scanner(anouncementFile);
            System.out.println("Anouncements:\n");
            String currentAnouncement;
            while (anouncement.hasNextLine()) { 
                currentAnouncement = anouncement.nextLine();
                if(currentAnouncement.equals("")){
                    System.out.println("No Anouncements");
                    return;
                }
                System.out.println(anouncement.nextLine());
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void sendMessage(String message){
        try{
            String filepath = "C:\\oop_project\\chat\\" + getFarmerID() + ".txt";
        FileWriter messageWriter = new FileWriter(filepath,true);
        messageWriter.write("Farmer: " + message + "\n");
        messageWriter.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public void seeMessage(){
        try{
            String filepath = "C:\\oop_project\\chat\\" + getFarmerID() + ".txt";
            File messageFile = new File(filepath);
        Scanner reader = new Scanner(messageFile);
        String currentLine = new String();
        while (reader.hasNextLine()==true){
            currentLine=reader.nextLine();
        }
        if (currentLine.charAt(0)=='F'){
            System.out.println("No updates from the government");
            return;
        }
        else {
            System.out.println(currentLine);
        }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}


