import java.io.BufferedReader;
import java.io.FileReader;

public class printBill {
        public static void print() {
                // ask for cid and month and year and print a bill
                byte month;
                short year;
                String cid;
                int counter = 0;
                String utility;
                try {
                        System.out.println("Enter cid");
                        cid = System.console().readLine().toUpperCase();
                        System.out.println("Enter month in single digit");
                        month = Byte.parseByte(System.console().readLine());
                        System.out.println("Enter year");
                        year = Short.parseShort(System.console().readLine());
                        System.out.println("Enter utility");
                        utility = System.console().readLine().toLowerCase();
                } catch (Exception e) {
                        System.out.println("Invalid input");
                        return;
                }
                String fileName = "db\\" + month + "" + year + ".txt";

                try {
                        FileReader fr = new FileReader(fileName);
                        BufferedReader br = new BufferedReader(fr);
                        String line;
                        String[] cids;
                        while ((line = br.readLine()) != null) {
                                cids = line.split(",");
                                if (cid.equals(cids[0])) {
                                        counter++;
                                        String[] tokens = line.split(",");
                                        /*
                                         * generate a bill in the format of
                                         * Cid ,
                                         * utility ,
                                         * month year ,
                                         * meterReading ,
                                         * bill amount
                                         * gst
                                         */
                                        ////////////////////////////////////////////////////////////////////////////////////////////////
                                        switch (utility) {
                                                case "electricity":
                                                        double gst = Double.parseDouble(tokens[2])
                                                                        - ((Double.parseDouble(tokens[2])) * (0.8547));
                                                        System.out.println(
                                                                        "------------------------------------------------------------------------");
                                                        System.out.println(
                                                                        "\nConsumer ID: " + tokens[0] + "\nName: "
                                                                                        + tokens[1] + "\nUtility: "
                                                                                        + utility
                                                                                        + "\nMonth/Year: " + month + "/"
                                                                                        + year + "\nMeter Reading: "
                                                                                        + tokens[2]
                                                                                        + "\nGST: " + gst
                                                                                        + "\nNet Bill Amount: Rs"
                                                                                        + tokens[7]);
                                                        System.out.println(
                                                                        "------------------------------------------------------------------------");
                                                        break;
                                                case "gas":
                                                        gst = Double.parseDouble(tokens[3])
                                                                        - ((Double.parseDouble(tokens[3])) * (0.8547));
                                                        System.out.println(
                                                                        "------------------------------------------------------------------------");
                                                        System.out.println(
                                                                        "\nConsumer ID: " + tokens[0] + "\nName: "
                                                                                        + tokens[1] + "\nUtility: "
                                                                                        + utility
                                                                                        + "\nMonth/Year: " + month + "/"
                                                                                        + year + "\nMeter Reading: "
                                                                                        + tokens[3]
                                                                                        + "\nGST: " + gst
                                                                                        + "\nNet Bill Amount: Rs"
                                                                                        + tokens[8]);
                                                        System.out.println(
                                                                        "------------------------------------------------------------------------");
                                                        break;
                                                case "water":
                                                        gst = Double.parseDouble(tokens[4])
                                                                        - ((Double.parseDouble(tokens[4])) * (0.8547));
                                                        System.out.println(
                                                                        "------------------------------------------------------------------------");
                                                        System.out.println(
                                                                        "\nConsumer ID: " + tokens[0] + "\nName: "
                                                                                        + tokens[1] + "\nUtility: "
                                                                                        + utility
                                                                                        + "\nMonth/Year: " + month + "/"
                                                                                        + year + "\nMeter Reading: "
                                                                                        + tokens[4]
                                                                                        + "\nGST: " + gst
                                                                                        + "\nNet Bill Amount: Rs"
                                                                                        + tokens[9]);
                                                        System.out.println(
                                                                        "------------------------------------------------------------------------");
                                                        break;
                                                case "phone":
                                                        gst = Double.parseDouble(tokens[5])
                                                                        - ((Double.parseDouble(tokens[5])) * (0.8547));
                                                        System.out.println(
                                                                        "------------------------------------------------------------------------");
                                                        System.out.println(
                                                                        "\nConsumer ID: " + tokens[0] + "\nName: "
                                                                                        + tokens[1] + "\nUtility: "
                                                                                        + utility
                                                                                        + "\nMonth/Year: " + month + "/"
                                                                                        + year + "\nMeter Reading: "
                                                                                        + tokens[5]
                                                                                        + "\nGST: " + gst
                                                                                        + "\nNet Bill Amount: Rs"
                                                                                        + tokens[10]);
                                                        System.out.println(
                                                                        "------------------------------------------------------------------------");
                                                        break;
                                                case "internet":
                                                        gst = Double.parseDouble(tokens[6])
                                                                        - ((Double.parseDouble(tokens[6])) * (0.8547));
                                                        System.out.println(
                                                                        "------------------------------------------------------------------------");
                                                        System.out.println(
                                                                        "\nConsumer ID: " + tokens[0] + "\nName: "
                                                                                        + tokens[1] + "\nUtility: "
                                                                                        + utility
                                                                                        + "\nMonth/Year: " + month + "/"
                                                                                        + year + "\nMeter Reading: "
                                                                                        + tokens[6]
                                                                                        + "\nGST: " + gst
                                                                                        + "\nNet Bill Amount: Rs"
                                                                                        + tokens[11]);
                                                        System.out.println(
                                                                        "------------------------------------------------------------------------");
                                                        break;
                                                default:
                                                        System.out.println("Invalid Utility");
                                                        return;
                                        }
                                }
                        }
                        if (counter == 0) {
                                System.out.println("No record found");
                        }
                        br.close();
                        fr.close();

                } catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage());
                        return;

                }
                return;
        }

}
