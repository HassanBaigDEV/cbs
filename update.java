import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class update {
    static void updateRecord() {
        String cid;
        byte month;
        int year;
        String fileName;
        /*
         * get the record of that specific cid from that file and replace it with the
         * record changed
         */

        /* get the cid from the user */
        try {
            System.out.println("Enter the cid");
            cid = System.console().readLine().toUpperCase();
            System.out.println("Enter the month in single digit");
            month = Byte.parseByte(System.console().readLine());
            System.out.println("Enter the year");
            year = Integer.parseInt(System.console().readLine());
            fileName = "db\\" + month + "" + year + ".txt";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        File f1 = new File("db\\tempt.txt");
        if (f1.exists()) {
            boolean tempDLT = f1.delete();
            if (tempDLT) {
            }
        }

        double meterReading = 0;
        double unit;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);
            String line;
            String cids[];
            double bill = 0;
            while ((line = br.readLine()) != null) {
                cids = line.split(",");
                if (cid.equals(cids[0])) {
                    System.out.println(line);
                    System.out.println("Enter what utility reading you want to update");
                    String choiceUpdate = System.console().readLine();
                    String utility = choiceUpdate.toLowerCase();

                    switch (utility) {
                        case "electricity":
                            System.out.println("Enter the new reading");
                            meterReading = Double.parseDouble(System.console().readLine());
                            if (meterReading != Double.parseDouble(cids[2])) {
                                unit = meterReading - Double.parseDouble(cids[2]);
                                bill = calculateBill.electricity(unit);
                            }

                            double new_bill = Double.parseDouble(cids[7]) + bill;
                            cids[2] = "" + meterReading;
                            cids[7] = "" + new_bill;
                            break;
                        case "gas":
                            System.out.println("Enter the new reading");
                            meterReading = Double.parseDouble(System.console().readLine());
                            if (meterReading != Double.parseDouble(cids[3])) {
                                unit = meterReading - Double.parseDouble(cids[3]);
                                bill = calculateBill.gas(unit);
                            }
                            double new_bill1 = Double.parseDouble(cids[8]) + bill;
                            cids[8] = "" + new_bill1;
                            cids[3] = "" + meterReading;
                            break;
                        case "water":
                            System.out.println("Enter the new reading");
                            meterReading = Double.parseDouble(System.console().readLine());
                            if (meterReading != Double.parseDouble(cids[4])) {
                                unit = meterReading - Double.parseDouble(cids[4]);
                                bill = calculateBill.water(unit);
                            }
                            double new_bill2 = Double.parseDouble(cids[9]) + bill;
                            cids[9] = "" + new_bill2;
                            cids[4] = "" + meterReading;
                            break;
                        case "phone":
                            System.out.println("Enter the number of local calls");
                            int localPhoneCalls = Integer.parseInt(System.console().readLine());
                            System.out.println("Enter the number of international calls");
                            int internationalPhoneCalls = Integer.parseInt(System.console().readLine());
                            meterReading = localPhoneCalls + internationalPhoneCalls;
                            if (meterReading != Double.parseDouble(cids[5])) {
                                int units = (int) (meterReading - Integer.parseInt(cids[5]));
                                bill = calculateBill.phone(units / 2 - localPhoneCalls,
                                        units / 2 - internationalPhoneCalls);
                            }
                            double new_bill3 = Double.parseDouble(cids[10]) + bill;
                            cids[10] = "" + new_bill3;

                            cids[5] = "" + meterReading;
                            break;
                        case "internet":
                            System.out.println("Enter the new reading");
                            meterReading = Integer.parseInt(System.console().readLine());
                            if (meterReading != Integer.parseInt(cids[6])) {
                                int units = (int) meterReading - Integer.parseInt(cids[6]);
                                bill = calculateBill.internet(units);
                                break;
                            }
                            double new_bill4 = Double.parseDouble(cids[11]) + bill;
                            cids[11] = "" + new_bill4;

                            cids[6] = "" + meterReading;
                            break;
                        default:
                            System.out.println("Invalid choice");
                            br.close();
                            bw.close();
                            fw.close();
                            f1.delete();
                            return;
                    }
                    bw.write(cids[0] + "," + cids[1] + "," + cids[2] + "," + cids[3] + "," + cids[4] + "," + cids[5]
                            + "," + cids[6] + "," + cids[7] + "," + cids[8] + "," + cids[9] + "," + cids[10] + ","
                            + cids[11]);
                    bw.newLine();
                } else {
                    bw.write(line);
                    bw.newLine();

                }
            }
            bw.close();
            fw.close();
            br.close();
            fr.close();

            File f = new File(fileName);
            boolean success = f.delete();
            boolean success1 = f1.renameTo(f);

            if (success && success1) {
                System.out.println("File updated");
                return;
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
