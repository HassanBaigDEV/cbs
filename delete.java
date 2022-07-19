import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;

public class delete {
    static void deleteRecords() {
        /* get the cid from the user */
        System.out.println();
        System.out.println("NOTE: This will delete the record of the cid you entered from the file of latest month");
        System.out.println("Enter the cid");
        String cid = System.console().readLine().toUpperCase();
        // get current month and year using Calendar class
        Calendar cal = Calendar.getInstance();
        byte month = (byte) cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        String fileName ="db\\"+ month + "" + year + ".txt";
        File f1 = new File("db\\temp.txt");
        if (f1.exists()) {
            boolean tempDLT = f1.delete();
            if (tempDLT) {
            } 
        }

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String cids[];
            String choice = "n";

            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);
   
            while ((line = br.readLine()) != null) {
                cids = line.split(",");
                if (cid.equals(cids[0])) {

                    System.out.println(line);
                    System.out.println("Are you sure you want to delete this record? (y/n)");
                    choice = System.console().readLine();
                    if (choice.equalsIgnoreCase("y")) {
                        System.out.println("Record deleted");
                        
                    } else {
                        System.out.println("Record not deleted");
                        bw.write(line);
                        bw.newLine();
                    }
                } else {    
                    bw.write(line);
                    bw.newLine();
                }

            }
            br.close();
            fr.close();
            bw.close();
            fw.close();


            File f = new File(fileName);
            boolean success = f.delete();
            boolean success1 = f1.renameTo(f);
        
            if (success && success1) {
                System.out.println("File updated");
            } else {
                System.out.println("File not updated.TRY AGAIN.");
                return;
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return;
        }

    }

}
