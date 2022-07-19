/*singJava file  IO, implementa smallmenu-driven databasemanagement system calledCombined Billing System(CBS)  for a  housing  project  comprising  of  ten  blocks  ranging from  block  A  to  J,  each  block  comprises  offour  sub-blocks  1  to  4,  each  sub-block comprisesof  ten  streets  1  to  10,  each  street  has  20  houses, each  house  comprises  of three portions(Ground, 1st, and 2ndfloor), and each portion have been provided four utilities (e.g. gas, electricity, water, and phone).


Each months data will be stored in that months file and address will be auto generated based on customer id
the data of all the users already exists from january 2018 to april 2022
the previous months meter reading will be sutracted by the one enterd by the user to get this months readings from which the bill will be calculated using tariff
Electricity
Less than100 Flat Rs.10/-per unit
Between 100-199 Rs.1000/-+ for units > 100, Rs.15/-per unit
 Between 200-300Rs.3000/-+ for units > 200, Rs.18/-per unit
 Above 300 Flat Rs.25/-per unit

 Water 
Up to 1000 liters Rs.400/-
 1001-2000 liters Rs. 1000/-
 Above 2000liters Rs. 1000/-plus Rs. 1.5/liter

 Phone/internet 
 Calls:
 i)Local
 Rs.5/-per minutes
 ii)International
 Rs.7/-per minute
 Internet:
 Rs.10/-per GB 
 */

/*
1.read the data from the previous month’s data file into a multidimensionalarray2.getthecurrent month’sreadingsfrom keyboardfor each utility3.calculate  and  displaya combined bill for the  residents  of each portionbased  on  the consumptionof each utilityand correspondingbillcalculationcriteria/tariff. create current month’s data file storing the residents’records kept inthe array and replacing boththe previous month’sreadingsand bill amounts with the current month’s readings and bill amountsto be used in the next month.Apart from its primary objective described above, the system must have provisionsfor searching, modification/updation, deletion of any record as well as generate various reports such as:1.the maximum and/or minimum bill for a specificutilityor house or street or sub-block, or block.2.Month-wise billingreportfor a specific consumerfor a specified period for all utilities3.Month-wise billingreportfor a specific consumerfor a specified period for a particular utility4.Detailed report of consumers not using all or a specific utility between a specifiedperiod.5.Yearly and half-yearly reports based on the total bill for each consumer */

import java.io.*;

public class cbs {

    public static void main(String[] args) throws IOException {

        int choice;
        do {
            System.out.println();
            System.out.println("1. Add a new record");
            System.out.println("2. Update a record");
            System.out.println("3. Delete a record");
            System.out.println("4. Print Bill");
            System.out.println("5. Generate Report/Display Records");
            System.out.println("6. Add all record");
            System.out.println("7. Display all records");
            System.out.println("8. Exit");
            System.out.println("Enter your choice");
            try {
                choice = Integer.parseInt(System.console().readLine());
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                choice = 0;
            }

            switch (choice) {
                case 1:
                    addRecords.add();
                    break;
                case 2:
                    update.updateRecord();
                    break;
                case 3:
                    delete.deleteRecords();
                    break;
                case 4:
                    printBill.print();
                    break;
                case 5:
                    reports.generateReport();
                    break;
                case 6:
                    add_All.addAllRecords();
                    break;
                case 7:
        
                    displayAll.displayAllRecords();
                    break;
                case 8:
                    System.out.println("Exiting");
                    System.exit(1);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 1 || choice != 2 || choice != 3 || choice != 4 || choice != 5 || choice != 6 || choice != 7);
    }

}
