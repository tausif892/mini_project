import myPackage.Farmer;
import myPackage.Government;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Farmer farmer=new Farmer();
        Scanner sc=new Scanner(System.in);
        System.err.println("Enter name:");
        String Name=sc.nextLine();
        farmer.setFarmerName(Name);
        System.err.println("Enter ID:");
        String id=sc.nextLine();
        farmer.setFarmerID(id);
        //farmer.Register();
        // farmer.putGrivence("Too much rainfall");
        // farmer.getAnouncement();
        // farmer.putGrivence("There has been delayed payment of subsidies");
        farmer.sendMessage("This is my first message");
        farmer.seeMessage();
        // farmer.deleteFarmer("FA123");
        Government gov =new Government();
        //gov.viewFarmer();
        gov.makeAnnouncement("The prices of crops have been reduced");
        // gov.seeGrievence();
        gov.sendMessage("FA123", "We are with you");
        gov.seeMessage("FA123");
        farmer.sendMessage("This is my first message");
        gov.seeMessage("FA123");
        gov.sendMessage("FA123", "We are with you");
        farmer.seeMessage();
    }
    
}
