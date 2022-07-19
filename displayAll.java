import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Scanner;

public class displayAll {
    static void displayAllRecords(){
        System.out.println();
        System.out.println("NOTE: This will display all the records till the latest month");
        // get current month and year using Calendar class
        Calendar cal = Calendar.getInstance();
        byte month = (byte) cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        String fileName = "db\\" + month + "" + year + ".txt";
        File f = new File(fileName);
        
        for (int j = 2018; j <= 2022; j++) {
            for(int i=1;i<=12;i++){
                String fileName1 = "db\\" + i + "" + j + ".txt";
                File f1 = new File(fileName1);
                f1 = new File(fileName);
                if (f1.exists()) {
                    Scanner sc;
                try {
                    sc = new Scanner(f1);
                    String line = sc.nextLine();
                    String[] line_split = line.split(",");

                    while (sc.hasNextLine()) {
                        line = sc.nextLine();
                        line_split = line.split(",");
                        
                            System.out.println(
                                    "------------------------------------------------------------------------");
                            System.out.println("\nConsumer ID: " + line_split[0] + "," + line_split[1]
                                    + "\nMonth/Year: " + i + "/" + j + "\nMeter Reading For Electricity: "
                                    + line_split[2] + "\nGST: " + line_split[7]
                                    + "\nNet Bill Amount for Electricity: Rs"
                                    + line_split[7] + "\nMeter Reading For Gas: " + line_split[3] + "\nGST: "
                                    + line_split[8] + "\nNet Bill Amount for Gas: Rs" + line_split[8] + ","
                                    + "Meter Reading For Water: " + line_split[4] + "\nGST: " + line_split[9]
                                    + "\nNet Bill Amount for Water: Rs" + line_split[9] + ","
                                    + "Meter Reading For Phone: " + line_split[5] + "\nGST: " + line_split[10]
                                    + "\nNet Bill Amount for Phone: Rs" + line_split[10] + ","
                                    + "Meter Reading For Internet: " + line_split[6] + "\nGST: " + line_split[11]
                                    + "\nNet Bill Amount for Internet: Rs" + line_split[11]);
                            System.out.println(
                                    "------------------------------------------------------------------------");
                        }
                    
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                }
            }
        }

        
    }
}
