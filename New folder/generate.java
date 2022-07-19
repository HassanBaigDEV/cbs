/*
///////////////////////////////////////////////////////////////////
NOTR:
THIS METHOD IS ONLY USED TO GENERATE NEW DATA ENTRIES AND/OR FILES 
TO BE USED IN THE TESTING OF THE PROGRAM.
THE METHOD IS NOT USED IN THE ACTUAL PROGRAM.
PREVIOUS FILE SHOULD EXIST BEFORE ACTUALLY RUNNING THIS METHOD.
///////////////////////////////////////////////////////////////////
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class generate {
    static double bille;
    static double billw;
    static double billg;
    static double billp;
    static double billI;
    static double meterReadingI = 0;
    static double unitsE = 0;
    static double unitsw = 0;
    static double unitsg = 0;
    static int unitsp = 0;
    static int unitsI = 0;
    static double meterReadingW = 0;
    static double meterReadingG = 0;
    static double meterReadingP = 0;
    static double meterReadingE = 0;
    static int unitspl = 0;
    static int unitspI = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {

        /*
         * run a for loop that randomly generates data fro cid 011 to 0410 for each
         * utility Electricity,Phone,Internet,Water ,gas in 12018 with month jan and
         * year as 2018 and input a meter reading and calculate the bill from that meter
         * reading based on the utility
         */

        String year[] = { "2023" };
        String month[] = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec" };

        for (int k = 0; k < year.length; k++) {
            // String year1 = year[k];
            for (int j = 0; j < month.length; j++) {
                int priv_month;
                int priv_year;
                // double bill = 0;

                if (j == 0) {
                    priv_month = 12;
                    priv_year = Integer.valueOf(year[k]) - 1;
                } else {
                    priv_month = j;
                    priv_year = Integer.valueOf(year[k]);
                }
                // create empty arraylist ids
                // ArrayList<String> ids = new ArrayList<String>();

                Random rnd = new Random();

                meterReadingI = 0.0;
                unitsE = 0;
                unitsw = 0;
                unitsg = 0;
                unitsp = 0;
                unitsI = 0;
                meterReadingW = 0.0;
                meterReadingG = 0.0;
                meterReadingP = 0.0;
                meterReadingE = 0.0;

                FileReader fr1 = new FileReader("" + priv_month + priv_year + ".txt");
                BufferedReader br1 = new BufferedReader(fr1);
                String line1;

                String cids1[];

                FileReader fr2 = new FileReader("clientRec.txt");
                BufferedReader br2 = new BufferedReader(fr2);
                String line2;
                String cids2[];

                // read next line on every cid

                while ((line1 = br1.readLine()) != null && (line2 = br2.readLine()) != null) {

                    // split and add in cids1
                    cids1 = line1.split(",");
                    meterReadingE = Double.valueOf(cids1[2]);
                    meterReadingG = Double.valueOf(cids1[3]);
                    meterReadingW = Double.valueOf(cids1[4]);
                    meterReadingP = Double.valueOf(cids1[5]);
                    meterReadingI = Double.valueOf(cids1[6]);
                    cids2 = line2.split(",");

                    unitsE = (double) rnd.nextInt(700) + 100;
                    bille = calculateBill.electricity(unitsE);

                    unitsw = (double) rnd.nextInt(2000) + 100;
                    billw = calculateBill.water(unitsw);

                    unitsg = (double) rnd.nextInt(400) + 40;
                    billg = calculateBill.gas(unitsg);

                    unitspl = rnd.nextInt(100) + 20;
                    unitspI = rnd.nextInt(60) + 20;
                    unitsp = unitspl + unitspI;
                    billp = calculateBill.phone(unitspI, unitspI);

                    unitsI = rnd.nextInt(100) + 20;
                    billI = calculateBill.internet(unitsI);

                    // String password = generateRandomPassword(rnd.nextInt(4)+3);
                    String fileName = "db1\\" + (j + 1) + year[k] + ".txt";

                    try {

                        FileWriter fw = new FileWriter(fileName, true);
                        BufferedWriter bw = new BufferedWriter(fw);

                        bw.write(cids2[0] + "," + cids2[1] + "," +
                                +(Math.addExact((int) meterReadingE, (int) unitsE)) + ","
                                + (Math.addExact((int) meterReadingG, (int) unitsg))
                                + "," + (Math.addExact((int) meterReadingW, (int) unitsw))
                                + ","
                                + unitsp + ","
                                + unitsI + ","
                                + bille + ","
                                + billg + "," + billw + "," + billp + "," + billI);
                        bw.newLine();
                        bw.close();

                    } catch (Exception b) {
                        System.out.println("UNABLE TO WRITE TO THE FILE. TRY AGAIN!!");
                        System.exit(0);

                    }
                }

            }

        }
    }

}
