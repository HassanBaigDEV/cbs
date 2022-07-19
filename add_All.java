import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class add_All {
    static void addAllRecords() {
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
        byte priv_month;
        int priv_year;
        String name = "";

        System.out.println();

        Calendar cal = Calendar.getInstance();
        month = (byte) cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        String fileName = "db\\" + month + "" + year + ".txt";
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

        String blocks[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" }; // A to J
        String streets[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }; // 0 to 9
        String houses[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
                "16",
                "17", "18", "19", "20" }; // 0 to 20
        String portions[] = { "1", "2", "3" }; // 1 to 3
        cid = "";
        String block = "";
        String subBlock[] = { "1", "2", "3", "4" };

        for (int i = 0; i < blocks.length; i++) {
            block = blocks[i];
            for (int j = 0; j < subBlock.length; j++) {
                for (int k = 0; k < streets.length; k++) {
                    for (int l = 0; l < houses.length; l++) {
                        for (int m = 0; m < portions.length; m++) {
                            cid = block + subBlock[j] + streets[k] + houses[l] + portions[m];
                            System.out.println("Entering Data for:" + cid);
                            try {
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
                                System.out.println("Invalid input");
                                return;
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
                                        name = data[1];
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
                                FileWriter fw = new FileWriter(fileName, true);
                                BufferedWriter bw = new BufferedWriter(fw);
                                FileReader fr = new FileReader(fileName);
                                BufferedReader br = new BufferedReader(fr);
                                String line;
                                while ((line = br.readLine()) != null) {
                                    String[] data1 = line.split(",");
                                    if (data1[0].equals(cid)) {
                                        System.out.println("This CLient Record already exists");

                                    } else
                                        bw.write(cid + "," + name + "," + meterReadinge + "," + meterReadingg + ","
                                                + meterReadingw + ","
                                                + meterReadingp + "," + unitsI + "," + localPhoneCalls + ","
                                                + internationalPhoneCalls + ","
                                                + bille + "," + billg + "," + billw + "," + billp + "," + billI);
                                    bw.newLine();
                                    bw.close();
                                    br.close();
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
                }
            }
        }
    }

}
