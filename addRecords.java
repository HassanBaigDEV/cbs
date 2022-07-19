import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class addRecords {

    public static void add() {

        byte month;
        int year;
        String cid;
        double meterReadinge = 0;
        double meterReadingg = 0;
        int meterReadingp = 0;
        double meterReadingw = 0;
        double bille = 0;
        double billg = 0;
        double billw = 0;
        double billp = 0;
        double billI = 0;
        int localPhoneCalls = 0;
        int internationalPhoneCalls = 0;
        int unitsI;

        try {
            System.out.println("Enter the consumer id");
            cid = System.console().readLine().toUpperCase();
            try {
                FileReader fr = new FileReader("db\\clientRec.txt");
                BufferedReader br = new BufferedReader(fr);
                String line1;
                String cids1[];
                int i = 0;
                while ((line1 = br.readLine()) != null) {
                    cids1 = line1.split(",");
                    if (cid.equals(cids1[0])) {
                        i++;
                    }
                }
                br.close();
                if (i == 0) {
                    System.out.println("Invalid Consumer id ");
                    return;
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                return;
            }

            System.out.println("Enter the month in number");
            month = Byte.parseByte(System.console().readLine());
            System.out.println("Enter the year");
            year = Integer.parseInt(System.console().readLine());
            System.out.println("Enter the meter reading for electricity");
            meterReadinge = Double.parseDouble(System.console().readLine());
            System.out.println("Enter the meter reading for gas");
            meterReadingg = Double.parseDouble(System.console().readLine());
            System.out.println("Enter the meter reading for water");
            meterReadingw = Double.parseDouble(System.console().readLine());
            System.out.println("Enter the number of gbs used for internet");
            unitsI = Integer.parseInt(System.console().readLine());
            System.out.println("Enter the number of local phone calls made: ");
            localPhoneCalls = Integer.parseInt(System.console().readLine());
            System.out.println("Enter the number of international phone calls made: ");
            internationalPhoneCalls = Integer.parseInt(System.console().readLine());
            meterReadingp = localPhoneCalls + internationalPhoneCalls;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return;
        }

        /*
         * check if that months file exists and if it exists then check if the data
         * inside it exists or not if it deos then prompt if not then append the data to
         * the file of that monthyear and each utility under one id like a table if the
         * file does not exist then create a new file and append the data to it like a
         * table
         */

        File file = new File("db\\" + month + year + ".txt");
        if (file.exists()) {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line12;
                String cids12[];
                while ((line12 = br.readLine()) != null) {
                    cids12 = line12.split(",");
                    if (cid.equals(cids12[0])) {
                        System.out.println("Record already exists");
                        br.close();
                        return;
                    }
                }
                br.close();
                fr.close();
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                return;
            }

        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to create new  monthly file. Please Try again.");
                return;
            }
        }

        // get the previous months units from the file of prevous month if it exists and
        // subtract it from the current month's meter reading to get the current month's
        // units if the file does not exist then prompt the user to enter the previous
        // months meter reading

        byte priv_month;
        int priv_year;
        if (month == 1) {
            priv_month = 12;
            priv_year = year--;
        } else {
            priv_month = month--;
            priv_year = year;
        }
        File file1 = new File("db\\" + priv_month + priv_year + ".txt");
        if (file1.exists()) {
        } else {
            System.out.println("Enter previous months record first:");
            try {
                file1.createNewFile();
                return;

            } catch (IOException e) {
                System.out.println("Couldn't create previous months file");
                return;
            }
        }

        double unitse = meterReadinge;
        double unitsg = meterReadingg;
        double unitsw = meterReadingw;
        try {
            FileReader fr = new FileReader(file1);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(cid)) {
                    if (meterReadinge >= Double.parseDouble(data[2]))
                        unitse = meterReadinge - Double.parseDouble(data[2]);
                    else {
                        System.out.println("Invalid meter reading");
                        br.close();
                        return;
                    }
                    if (meterReadingg >= Double.parseDouble(data[3]))
                        unitsg = meterReadingg - Double.parseDouble(data[3]);
                    else {
                        System.out.println("Invalid meter reading");
                        br.close();
                        return;
                    }
                    if (meterReadingw >= Double.parseDouble(data[4]))
                        unitsw = meterReadingw - Double.parseDouble(data[4]);
                    else {
                        System.out.println("Invalid meter reading");
                        br.close();
                        return;
                    }
                    if (unitsI < 0) {
                        System.out.println("Invalid meter reading");
                        br.close();
                        return;
                    }
                    if (meterReadingp < 0) {
                        System.out.println("Invalid meter reading");
                        br.close();
                        return;
                    }

                } else
                    System.out.println("This Consumer ID doesn't exist");
                    br.close();
                return;

            }
            br.close();
        } catch (IOException e) {
            System.out.println("Unable to read previous months file");
            return;
        }

        // calculate the units used in this month by using previous months units and
        // current months units and tariff

        bille = calculateBill.electricity(unitse);
        billg = calculateBill.gas(unitsg);
        billw = calculateBill.water(unitsw);
        billp = calculateBill.phone(localPhoneCalls, internationalPhoneCalls);
        billI = calculateBill.internet(unitsI);

        // write the bill to the file of that monthyear
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            FileReader fr = new FileReader("db\\clientRec.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(cid)) {
                    bw.write(cid + "," + data[1] + "," + meterReadinge + "," + meterReadingg + "," + meterReadingw + ","
                            + meterReadingp + "," + unitsI + "," + localPhoneCalls + "," + internationalPhoneCalls + ","
                            + bille + "," + billg + "," + billw + "," + billp + "," + billI);
                    bw.newLine();
                    bw.close();
                    br.close();
                    return;
                } else
                    System.out.println("This Consumer ID doesn't exist. Please TRY AGAIN");
                bw.close();
                br.close();
                return;
            }
            bw.close();
            br.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file. Pease try again");
           
            return;
        }

    }
}