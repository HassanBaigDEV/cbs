import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class reports {
    static void generateReport() {
        byte choice;
        do {
            try {
                System.out.println();
                System.out.println(
                        "1. Maximum and/or minimum bill for a specific utility or house  or block or sub-block.");
                System.out.println(
                        "2. Month-wise billing report for a specific consumer for a specified period for all utilities");
                System.out.println(
                        "3. Month-wise billing report for a specific consumer for a specified period for a particular utility");
                System.out.println(
                        "4. Detailed report of consumers not using all or a specific utility between a specified period.");
                System.out.println("5. Yearly and half-yearly reports based on the total bill for each consumer");
                System.out.println("6.Go back");
                System.out.println("Enter your choice");

                choice = Byte.parseByte(System.console().readLine());

            } catch (Exception e) {
                System.out.println("Invalid input");
                return;
            }
            switch (choice) {
                case 1:
                    max_min();
                    break;
                case 2:
                    month_wise_Allutility();
                    break;
                case 3:
                    month_wise_utility();
                    break;
                case 4:
                    not_using_utility();
                    break;
                case 5:
                    yearly_half_yearly();
                    break;
                case 6:
                    System.out.println("Going back");
                    return;
            }
        } while (choice != 1 || choice != 2 || choice != 3 || choice != 4 || choice != 5 || choice != 6);
    }

    private static void max_min() {
        System.out.println("1.Search by utility name");
        System.out.println("2.Search by cid number");
        System.out.println("3.Search by block name");
        System.out.println("4. Search by Sub-Block name");
        System.out.println("5.Search by street number");
        System.out.println("6.Search by house number");
        System.out.println("7.Search by portion number");
        System.out.println("8.Go back");
        System.out.println("Enter your choice");
        int choice;
        byte start_month;
        int start_year;

        try {
            choice = Integer.parseInt(System.console().readLine());
            System.out.println("Enter the month in single digit");
            start_month = Byte.parseByte(System.console().readLine());
            System.out.println("Enter the year");
            start_year = Integer.parseInt(System.console().readLine());
        } catch (Exception e) {
            System.out.println("Invalid input.TRY AGAIN");
            return;
        }
        switch (choice) {
            case 1:
                System.out.println("Enter the utility name");
                String utility_name = System.console().readLine();
                String utility_name_lower = utility_name.toLowerCase();

                String filename = "db\\" + start_month + "" + start_year + ".txt";
                File file = new File(filename);
                Scanner sc;
                try {
                    sc = new Scanner(file);
                    String line = sc.nextLine();
                    String[] line_split = line.split(",");

                    double max_bill = 0;
                    double min_bill = 0;

                    switch (utility_name_lower) {
                        case "electricity":
                            min_bill = Double.parseDouble(line_split[7]);
                            while (sc.hasNextLine()) {
                                line = sc.nextLine();
                                line_split = line.split(",");
                                double bill = Double.parseDouble(line_split[7]);
                                if (bill > max_bill) {
                                    max_bill = bill;
                                }
                                if (bill < min_bill) {
                                    min_bill = bill;
                                }
                            }
                            break;
                        case "gas":
                            min_bill = Double.parseDouble(line_split[8]);
                            while (sc.hasNextLine()) {
                                line = sc.nextLine();
                                line_split = line.split(",");
                                double bill = Double.parseDouble(line_split[8]);
                                if (bill > max_bill) {
                                    max_bill = bill;
                                }
                                if (bill < min_bill) {
                                    min_bill = bill;
                                }
                            }
                            break;
                        case "water":
                            min_bill = Double.parseDouble(line_split[9]);
                            while (sc.hasNextLine()) {
                                line = sc.nextLine();
                                line_split = line.split(",");
                                double bill = Double.parseDouble(line_split[9]);
                                if (bill > max_bill) {
                                    max_bill = bill;
                                }
                                if (bill < min_bill) {
                                    min_bill = bill;
                                }
                            }
                            break;
                        case "phone":
                            min_bill = Double.parseDouble(line_split[10]);
                            while (sc.hasNextLine()) {
                                line = sc.nextLine();
                                line_split = line.split(",");
                                double bill = Double.parseDouble(line_split[10]);
                                if (bill > max_bill) {
                                    max_bill = bill;
                                }
                                if (bill < min_bill) {
                                    min_bill = bill;
                                }
                            }
                            break;
                        case "internet":
                            min_bill = Double.parseDouble(line_split[11]);
                            while (sc.hasNextLine()) {
                                line = sc.nextLine();
                                line_split = line.split(",");
                                double bill = Double.parseDouble(line_split[11]);
                                if (bill > max_bill) {
                                    max_bill = bill;
                                }
                                if (bill < min_bill) {
                                    min_bill = bill;
                                }
                            }
                            break;
                        default:
                            System.out.println("Invalid utility name");
                            break;
                    }

                    System.out.println("Maximum bill for " + utility_name_lower + " is " + max_bill);
                    System.out.println("Minimum bill for " + utility_name_lower + " is " + min_bill);

                } catch (FileNotFoundException e) {
                    System.out.println("ERROR:Unable to read Records");
                    return;
                }
                sc.close();
                break;
            case 2:
                System.out.println("Enter the house number/CID");
                String house_number = (System.console().readLine()).toUpperCase();

                filename = "db\\" + start_month + "" + start_year + ".txt";
                file = new File(filename);
                try {
                    sc = new Scanner(file);
                    String line;
                    String[] line_split;
                    double max_bill = 0;
                    double min_bill = 0;
                    int counter = 0;
                    while (sc.hasNextLine()) {
                        line = sc.nextLine();
                        line_split = line.split(",");
                        if (line_split[0].equals(house_number)) {
                            counter++;
                            min_bill = Double.parseDouble(line_split[7]);
                            // compare all the bills from index 7 to 11 for the same cid and find the max
                            // and min
                            for (int i = 7; i < 12; i++) {
                                double bill = Double.parseDouble(line_split[i]);
                                if (bill > max_bill) {
                                    max_bill = bill;
                                }
                                if (bill < min_bill) {
                                    min_bill = bill;
                                }
                            }
                        }
                    }
                    if (counter == 0) {
                        System.out.println("No records found");
                        return;
                    } else {
                        System.out.println("Maximum bill for " + house_number + " is " + max_bill);
                        System.out.println("Minimum bill for " + house_number + " is " + min_bill);
                    }

                    sc.close();
                } catch (Exception e) {
                    System.out.println("ERROR:Unable to read Records(Either file not found or invalid house number)");
                    return;

                }
                sc.close();
                break;

            case 3:
                String block_name = "";
                System.out.println("Enter the block name");

                try {
                    block_name = (System.console().readLine()).toUpperCase();
                    if (block_name.length() > 3 || block_name.length() < 1 || block_name == null) {
                        System.out.println("Invalid block name");
                        return;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input.TRY AGAIN");
                    return;
                }

                filename = "db\\" + start_month + "" + start_year + ".txt";
                file = new File(filename);
                try {
                    sc = new Scanner(file);
                    String line;
                    String[] line_split;
                    double max_bill = 0;
                    double min_bill = 0;
                    int counter = 0;
                    while (sc.hasNextLine()) {
                        line = sc.nextLine();
                        line_split = line.split(",");
                        if (line_split[0].contains(block_name)) {
                            counter++;
                            double bill = Double.parseDouble(line_split[7]);
                            for (int i = 7; i < 12; i++) {
                                double temp_bill = Double.parseDouble(line_split[i]);
                                if (temp_bill > max_bill) {
                                    max_bill = temp_bill;
                                }
                                if (temp_bill < min_bill) {
                                    min_bill = bill;
                                }
                            }
                        }
                    }
                    if (counter == 0) {
                        System.out.println("No records found for the block " + block_name);
                        return;
                    } else {
                        System.out.println("Maximum bill for " + block_name + " is " + max_bill);
                        System.out.println("Minimum bill for " + block_name + " is " + min_bill);
                    }
                } catch (Exception e) {
                    System.out.println("ERROR:Unable to read Records(Either file not found or invalid house number)");
                    return;
                }
                break;
            case 4:
                String block_name1 = "";
                String sub_block_name = "";
                try {
                    System.out.println("Enter Block name");
                    block_name1 = (System.console().readLine()).toUpperCase();
                    System.out.println("Enter the Sub-block number");
                    sub_block_name = (System.console().readLine());
                    filename = "db\\" + start_month + "" + start_year + ".txt";
                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
                    return;
                }
                file = new File(filename);

                try {
                    sc = new Scanner(file);
                    String line;
                    String[] line_split;
                    double max_bill = 0;
                    double min_bill = 0;
                    int counter = 0;
                    while (sc.hasNextLine()) {
                        line = sc.nextLine();
                        line_split = line.split(",");
                        if (line_split[0].contains("" + block_name1 + "" + sub_block_name)) {
                            counter++;
                            min_bill = Double.parseDouble(line_split[7]);
                            // compare all the bills from index 7 to 11 for the same cid and find the max
                            // and min
                            for (int i = 7; i < 12; i++) {
                                double bill = Double.parseDouble(line_split[i]);
                                if (bill > max_bill) {
                                    max_bill = bill;
                                }
                                if (bill < min_bill) {
                                    min_bill = bill;
                                }
                            }
                        }
                    }
                    if (counter == 0) {
                        System.out.println("No records found");
                        return;
                    } else {
                        System.out.println("Maximum bill for Block " + block_name1 + " Sub-block " + sub_block_name
                                + " is " + max_bill);
                        System.out.println("Minimum bill for Block " + block_name1 + " Sub-block " + sub_block_name
                                + " is " + min_bill);
                    }

                } catch (Exception e) {
                    System.out.println("ERROR:Unable to read Records(Either file not found or invalid house number)");
                    return;
                }
                break;
            case 5:
                String block_name2 = "";
                String sub_block_name1 = "";
                String street = "";
                try {
                    System.out.println("Enter Block name");
                    block_name2 = (System.console().readLine()).toUpperCase();
                    System.out.println("Enter the Sub-block number");
                    sub_block_name1 = (System.console().readLine());
                    System.out.println("Enter the street number(0-9)");
                    street = (System.console().readLine());
                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
                    return;
                }
                filename = "db\\" + start_month + "" + start_year + ".txt";
                file = new File(filename);
                try {
                    sc = new Scanner(file);
                    String line;
                    String[] line_split;
                    double max_bill = 0;
                    double min_bill = 0;
                    int counter = 0;
                    while (sc.hasNextLine()) {
                        line = sc.nextLine();
                        line_split = line.split(",");
                        if (line_split[0].contains("" + block_name2 + "" + sub_block_name1 + "" + street)) {
                            counter++;
                            min_bill = Double.parseDouble(line_split[7]);
                            // compare all the bills from index 7 to 11 for the same cid and find the max
                            // and min
                            for (int i = 7; i < 12; i++) {
                                double bill = Double.parseDouble(line_split[i]);
                                if (bill > max_bill) {
                                    max_bill = bill;
                                }
                                if (bill < min_bill) {
                                    min_bill = bill;
                                }
                            }
                        }
                    }
                    if (counter == 0) {
                        System.out.println("No records found");
                        return;
                    } else {
                        System.out.println("Maximum bill for Block " + block_name2 + " Sub-block " + sub_block_name1
                                + "street" + street + " is " + max_bill);
                        System.out.println("Minimum bill for Block " + block_name2 + " Sub-block " + sub_block_name1
                                + "street" + street + " is " + min_bill);
                    }
                } catch (Exception e) {
                    System.out.println("ERROR:Unable to read Records(Either file not found or invalid house number)");
                    return;
                }

                break;
            case 6:
                String block_name3 = "";
                String sub_block2 = "";
                String street1 = "";
                String house_number1 = "";
                try {
                    System.out.println("Enter Block name");
                    block_name3 = (System.console().readLine()).toUpperCase();
                    System.out.println("Enter the Sub-block number");
                    sub_block2 = (System.console().readLine());
                    System.out.println("Enter the street number(0-9)");
                    street1 = (System.console().readLine());
                    System.out.println("Enter the house number(01-20)");
                    house_number1 = (System.console().readLine());
                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
                    return;
                }
                filename = "db\\" + start_month + "" + start_year + ".txt";
                file = new File(filename);
                try {
                    sc = new Scanner(file);
                    String line;
                    String[] line_split;
                    double max_bill = 0;
                    double min_bill = 0;
                    int counter = 0;
                    while (sc.hasNextLine()) {
                        line = sc.nextLine();
                        line_split = line.split(",");
                        if (line_split[0]
                                .contains("" + block_name3 + "" + sub_block2 + "" + street1 + "" + house_number1)) {
                            counter++;
                            min_bill = Double.parseDouble(line_split[7]);
                            // compare all the bills from index 7 to 11 for the same cid and find the max
                            // and min
                            for (int i = 7; i < 12; i++) {
                                double bill = Double.parseDouble(line_split[i]);
                                if (bill > max_bill) {
                                    max_bill = bill;
                                }
                                if (bill < min_bill) {
                                    min_bill = bill;
                                }
                            }
                        }
                    }
                    if (counter == 0) {
                        System.out.println("No records found");
                        return;
                    } else {
                        System.out.println("Maximum bill for Block " + block_name3 + " Sub-block " + sub_block2
                                + "street" + street1 + "house number" + house_number1 + " is " + max_bill);
                        System.out.println("Minimum bill for Block " + block_name3 + " Sub-block " + sub_block2
                                + "street" + street1 + "house number" + house_number1 + " is " + min_bill);
                    }
                } catch (Exception e) {
                    System.out.println("ERROR:Unable to read Records(Either file not found or invalid house number)");
                    return;
                }

                break;
            case 7:
                String block_name4 = "";
                String sub_block3 = "";
                String street2 = "";
                String house_number2 = "";
                String portion = "";
                try {
                    System.out.println("Enter Block name");
                    block_name4 = (System.console().readLine()).toUpperCase();
                    System.out.println("Enter the Sub-block number");
                    sub_block3 = (System.console().readLine());
                    System.out.println("Enter the street number(0-9)");
                    street2 = (System.console().readLine());
                    System.out.println("Enter the house number(01-20)");
                    house_number2 = (System.console().readLine());
                    System.out.println("Enter the portion(1-3)");
                    portion = (System.console().readLine());
                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
                    return;
                }
                filename = "db\\" + start_month + "" + start_year + ".txt";
                file = new File(filename);
                try {
                    sc = new Scanner(file);
                    String line;
                    String[] line_split;
                    double max_bill = 0;
                    double min_bill = 0;
                    int counter = 0;
                    while (sc.hasNextLine()) {
                        line = sc.nextLine();
                        line_split = line.split(",");
                        if (line_split[0]
                                .contains("" + block_name4 + "" + sub_block3 + "" + street2 + "" + house_number2 + ""
                                        + portion)) {
                            counter++;
                            min_bill = Double.parseDouble(line_split[7]);
                            // compare all the bills from index 7 to 11 for the same cid and find the max
                            // and min
                            for (int i = 7; i < 12; i++) {
                                double bill = Double.parseDouble(line_split[i]);
                                if (bill > max_bill) {
                                    max_bill = bill;
                                }
                                if (bill < min_bill) {
                                    min_bill = bill;
                                }
                            }
                        }
                    }
                    if (counter == 0) {
                        System.out.println("No records found");
                        return;
                    } else {
                        System.out.println("Maximum bill for Block " + block_name4 + " Sub-block " + sub_block3
                                + "street" + street2 + "house number" + house_number2 + "" + portion + " is "
                                + max_bill);
                        System.out.println("Minimum bill for Block " + block_name4 + " Sub-block " + sub_block3
                                + "street" + street2 + "house number" + house_number2 + "" + portion
                                + " is " + min_bill);
                    }
                } catch (Exception e) {
                    System.out.println("ERROR:Unable to read Records(Either file not found or invalid house number)");
                    return;
                }

                break;

            case 8:
                return;

            default:
                System.out.println("Invalid choice");
                return;
        }
        return;

    }

    private static void month_wise_utility() {
        byte start_month = 0;
        int start_year = 0;
        byte end_month = 0;
        int end_year = 0;
        String cid = "";
        String utility_name = "";

        try {
            System.out.println("Enter starting month: ");
            start_month = Byte.parseByte(System.console().readLine());
            System.out.println("Enter starting year: ");
            start_year = Integer.parseInt(System.console().readLine());
            System.out.println("Enter ending month: ");
            end_month = Byte.parseByte(System.console().readLine());
            System.out.println("Enter ending year: ");
            end_year = Integer.parseInt(System.console().readLine());
            System.out.println("Enter the CID");
            cid = (System.console().readLine());
            System.out.println("Enter the utility name");
            utility_name = (System.console().readLine()).toLowerCase();
        } catch (Exception e) {
            System.out.println("ERROR:Invalid input");
            return;
        }
        String filename;

        for (int j = start_year; j <= end_year; j++) {
            for (int i = start_month; i <= end_month; i++) {

                filename = "db\\" + i + "" + j + ".txt";

                File file = new File(filename);
                Scanner sc;
                try {
                    sc = new Scanner(file);
                    String line = sc.nextLine();
                    String[] line_split = line.split(",");

                    while (sc.hasNextLine()) {
                        line = sc.nextLine();
                        line_split = line.split(",");
                        if (line_split[0].equals(cid)) {

                            switch (utility_name) {
                                case "electricity":

                                    double gst = Double.parseDouble(line_split[7])
                                            - ((Double.parseDouble(line_split[7])) * (0.8547));

                                    System.out.println(
                                            "------------------------------------------------------------------------");
                                    System.out.println("\nConsumer ID: " + line_split[0] + "," + line_split[1]
                                            + "\nUtility: "
                                            + utility_name + "\nMonth/Year: " + i + "/" + j + "\nMeter Reading: "
                                            + line_split[2] + "\nGST: " + gst + "\nNet Bill Amount: Rs"
                                            + line_split[7]);
                                    System.out.println(
                                            "------------------------------------------------------------------------");
                                    break;
                                case "gas":
                                    double gst1 = Double.parseDouble(line_split[8])
                                            - ((Double.parseDouble(line_split[8])) * (0.8548));
                                    System.out.println(
                                            "------------------------------------------------------------------------");
                                    System.out.println("\nConsumer ID: " + line_split[0] + "," + line_split[1]
                                            + "\nUtility: "
                                            + utility_name + "\nMonth/Year: " + i + "/" + j + "\nMeter Reading: "
                                            + line_split[3] + "\nGST: " + gst1 + "\nNet Bill Amount: Rs"
                                            + line_split[8]);
                                    System.out.println(
                                            "------------------------------------------------------------------------");
                                    break;
                                case "water":
                                    double gst2 = Double.parseDouble(line_split[9])
                                            - ((Double.parseDouble(line_split[9])) * (0.9547));
                                    System.out.println(
                                            "------------------------------------------------------------------------");
                                    System.out.println("\nConsumer ID: " + line_split[0] + "," + line_split[1]
                                            + "\nUtility: "
                                            + utility_name + "\nMonth/Year: " + i + "/" + j + "\nMeter Reading: "
                                            + line_split[4] + "\nGST: " + gst2 + "\nNet Bill Amount: Rs"
                                            + line_split[9]);
                                    System.out.println(
                                            "------------------------------------------------------------------------");
                                    break;
                                case "phone":
                                    double gst3 = Double.parseDouble(line_split[10])
                                            - ((Double.parseDouble(line_split[10])) * (0.8547));
                                    System.out.println(
                                            "------------------------------------------------------------------------");
                                    System.out.println("\nConsumer ID: " + line_split[0] + "," + line_split[1]
                                            + "\nUtility: "
                                            + utility_name + "\nMonth/Year: " + i + "/" + j + "\nMeter Reading: "
                                            + line_split[5] + "\nGST: " + gst3 + "\nNet Bill Amount: Rs"
                                            + line_split[10]);
                                    System.out.println(
                                            "------------------------------------------------------------------------");
                                    break;
                                case "internet":
                                    double gst4 = Double.parseDouble(line_split[11])
                                            - ((Double.parseDouble(line_split[11])) * (0.8547));
                                    System.out.println(
                                            "------------------------------------------------------------------------");
                                    System.out.println("\nConsumer ID: " + line_split[0] + "," + line_split[1]
                                            + "\nUtility: "
                                            + utility_name + "\nMonth/Year: " + i + "/" + j + "\nMeter Reading: "
                                            + line_split[6] + "\nGST: " + gst4 + "\nNet Bill Amount: Rs"
                                            + line_split[11]);
                                    System.out.println(
                                            "------------------------------------------------------------------------");
                                    break;
                                default:
                                    System.out.println("Invalid Utility");
                                    sc.close();
                                    return;
                            }
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Unable to read records");
                    return;

                }

            }
        }
        return;
    }

    private static void month_wise_Allutility() {
        byte start_month = 0;
        int start_year = 0;
        byte end_month = 0;
        int end_year = 0;
        String cid = "";
        try {
            System.out.println("Enter starting month in single digits: ");
            start_month = Byte.parseByte(System.console().readLine());
            System.out.println("Enter starting year: ");
            start_year = Integer.parseInt(System.console().readLine());
            System.out.println("Enter ending month in single digits: ");
            end_month = Byte.parseByte(System.console().readLine());
            System.out.println("Enter ending year: ");
            end_year = Integer.parseInt(System.console().readLine());
            System.out.println("Enter the Consumer ID: ");
            cid = (System.console().readLine());
        } catch (Exception e) {
            System.out.println("ERROR:Invalid input");
            return;
        }

        String filename;
        for (int j = start_year; j <= end_year; j++) {
            for (int i = start_month; i <= end_month; i++) {
                filename = "db\\" + i + "" + j + ".txt";

                File file = new File(filename);
                Scanner sc;
                try {
                    sc = new Scanner(file);
                    String line = sc.nextLine();
                    String[] line_split = line.split(",");

                    while (sc.hasNextLine()) {
                        line = sc.nextLine();
                        line_split = line.split(",");
                        if (line_split[0].equals(cid)) {

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
                    }
                } catch (Exception e) {
                    System.out.println("Unable to read records");
                    return;
                }
            }
        }
        return;
    }

    private static void not_using_utility() {
        System.out.println("Enter starting month in single digits: ");
        byte start_month = Byte.parseByte(System.console().readLine());
        System.out.println("Enter starting year: ");
        int start_year = Integer.parseInt(System.console().readLine());
        System.out.println("Enter ending month in single digits: ");
        byte end_month = Byte.parseByte(System.console().readLine());
        System.out.println("Enter ending year: ");
        int end_year = Integer.parseInt(System.console().readLine());

        String filename;
        for (int j = start_year; j <= end_year; j++) {
            for (int i = start_month; i <= end_month; i++) {
                filename = "db\\" + i + "" + j + ".txt";

                File file = new File(filename);
                Scanner sc;
                try {
                    sc = new Scanner(file);
                    String line = sc.nextLine();
                    String[] line_split = line.split(",");

                    while (sc.hasNextLine()) {
                        line = sc.nextLine();
                        line_split = line.split(",");
                        if (line_split[7] == "0" && line_split[8] == "0" && line_split[9] == "0"
                                && line_split[10] == "0" && line_split[11] == "0") {
                            System.out.println(line_split[0] + "is not using any utility in" + i + "/" + j);
                        } else if (line_split[7] == "0" || line_split[8] == "0" || line_split[9] == "0"
                                || line_split[10] == "0" || line_split[11] == "0") {
                            if (line_split[7] == "0") {
                                System.out.println(line_split[0] + "is not using electricity in" + i + "/" + j);
                            }
                            if (line_split[8] == "0") {
                                System.out.println(line_split[0] + "is not using gas in" + i + "/" + j);
                            }
                            if (line_split[9] == "0") {
                                System.out.println(line_split[0] + "is not using water in" + i + "/" + j);
                            }
                            if (line_split[10] == "0") {
                                System.out.println(line_split[0] + "is not using phone in" + i + "/" + j);
                            }
                            if (line_split[11] == "0") {
                                System.out.println(line_split[0] + "is not using internet in" + i + "/" + j);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Unable to read records (Either file does not exist or invalid input)");
                    return;
                }
            }
        }
        return;
    }

    private static void yearly_half_yearly() {
        int choice = 0;
        do {
            System.out.println("1.First Half Year Report");
            System.out.println("2.Second Half Year Report");
            System.out.println("3.Yearly Report");
            System.out.println("Enter your choice: ");
            try {
                choice = Integer.parseInt(System.console().readLine());
            } catch (Exception e) {
                System.out.println("Invalid choice");
                return;
            }
        } while (choice != 1 && choice != 2 && choice != 3);
        int year = 0;

        System.out.println("Enter the Year: ");
        try {
            year = Integer.parseInt(System.console().readLine());
        } catch (Exception e) {
            System.out.println("Invalid year");
            return;
        }

        switch (choice) {
            case 1:
                String cid = "";
                int counter = 0;
                System.out.println("Enter the Consumer ID: ");
                try {
                    cid = (System.console().readLine()).toUpperCase();
                } catch (Exception e) {
                    System.out.println("Invalid Consumer ID");
                    return;
                }
                String filename;
                Scanner sc;
                double net_bill_amount = 0;
                try {
                    for (int i = 1; i <= 6; i++) {
                        filename = "db\\" + i + "" + year + ".txt";
                        File file = new File(filename);
                        sc = new Scanner(file);
                        String line;
                        String[] line_split;
                        while (sc.hasNextLine()) {
                            line = sc.nextLine();
                            line_split = line.split(",");
                            if (line_split[0].equals(cid)) {
                                counter++;

                                System.out.println(
                                        "------------------------------------------------------------------------");
                                System.out.println("\nConsumer ID: " + line_split[0] + "," + line_split[1]
                                        + "\nMonth/Year: " + i + "/" + year + "\nMeter Reading For Electricity: "
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
                                net_bill_amount = net_bill_amount + Double.parseDouble(line_split[7])
                                        + Double.parseDouble(line_split[8]) + Double.parseDouble(line_split[9])
                                        + Double.parseDouble(line_split[10]) + Double.parseDouble(line_split[11]);

                            }
                        }
                    }
                    if (counter == 0) {
                        System.out.println("No records found");
                        return;
                    } else {
                        System.out.println("\nTotal Net Bill Amount for this time period: Rs" + net_bill_amount);
                    }

                } catch (Exception e) {
                    System.out.println("Unable to read records");
                    return;
                }
                break;
            case 2:
                String cid1;
                int count3 = 0;
                System.out.println("Enter the Consumer ID: ");
                try {
                    cid1 = (System.console().readLine()).toUpperCase();
                } catch (Exception e) {
                    System.out.println("Invalid Consumer ID");
                    return;
                }
                String filename1;
                Scanner sc1;
                double net_bill_amount1 = 0;
                for (int i = 7; i <= 12; i++) {
                    filename1 = "db\\" + i + "" + year + ".txt";
                    try {
                        File file = new File(filename1);
                        sc1 = new Scanner(file);
                        String line;
                        String[] line_split;
                        while (sc1.hasNextLine()) {
                            line = sc1.nextLine();
                            line_split = line.split(",");
                            if (line_split[0].equals(cid1)) {
                                count3++;

                                System.out.println(
                                        "------------------------------------------------------------------------");
                                System.out.println("\nConsumer ID: " + line_split[0] + "," + line_split[1]
                                        + "\nMonth/Year: " + i + "/" + year + "\nMeter Reading For Electricity: "
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
                                net_bill_amount1 = net_bill_amount1 + Double.parseDouble(line_split[7])
                                        + Double.parseDouble(line_split[8]) + Double.parseDouble(line_split[9])
                                        + Double.parseDouble(line_split[10]) + Double.parseDouble(line_split[11]);
                            }
                        }
                        if (count3 == 0) {
                            System.out.println("No records found");
                            return;
                        }
                        System.out.println("\nTotal Net Bill Amount for this time Period: Rs" + net_bill_amount1);
                    } catch (Exception e) {
                        System.out.println("Unable to read records ERROR: " + e.getMessage());
                        return;
                    }
                }
                break;
            case 3:
                String cid2 = "";
                System.out.println("Enter the Consumer ID: ");
                double net_bill_amount2 = 0;
                try {
                    cid2 = (System.console().readLine()).toUpperCase();
                } catch (Exception e) {
                    System.out.println("Invalid Consumer ID");
                    return;
                }
                String filename2;
                Scanner sc2;
                int count2 = 0;
                for (int i = 1; i <= 12; i++) {
                    filename2 = "db\\" + i + "" + year + ".txt";
                    try {
                        File file = new File(filename2);
                        sc2 = new Scanner(file);
                        String line;
                        String[] line_split;
                        while (sc2.hasNextLine()) {
                            line = sc2.nextLine();
                            line_split = line.split(",");
                            if (line_split[0].equals(cid2)) {
                                count2++;
                                System.out.println(
                                        "------------------------------------------------------------------------");
                                System.out.println("\nConsumer ID: " + line_split[0] + "," + line_split[1]
                                        + "\nMonth/Year: " + i + "/" + year + "\nMeter Reading For Electricity: "
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
                                net_bill_amount2 = net_bill_amount2 + Double.parseDouble(line_split[7])
                                        + Double.parseDouble(line_split[8]) + Double.parseDouble(line_split[9])
                                        + Double.parseDouble(line_split[10]) + Double.parseDouble(line_split[11]);
                            }
                        }
                        if (count2 == 0) {
                            System.out.println("No records found");
                            return;
                        }
                    } catch (Exception e) {
                        System.out.println("Unable to read records ERROR: " + e.getMessage());
                        return;
                    }
                }
                break;
        }
        return;
    }

}
