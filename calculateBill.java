

public class calculateBill {

 
    static double bill;

    public static double electricity(double units) {
        if(units<0)
            bill = units * (-10);
        
        if (units == 0) {
            bill = 0;
        } 
        if (units < 100 && units>0) {
            bill = units * 10;
        } else if (units < 200) {
            bill = 1000 + (units - 100) * 15;
        } else if (units < 300) {
            bill = 3000 + (units - 200) * 18;
        } else if (units >= 300) {
            bill = units * 25;
        }
        bill = bill + 0.17 * bill;

        return bill;

    }

    public static double gas(double units) {

        
        double unitHm3 = (double) units / 100;
        // find mmbtu used
        double mmbtu = 0;
        // mmbtu=unitHm3*954/281.7385;
        if (unitHm3 < 0) {
            mmbtu = unitHm3 * 954 / 281.7385;
            bill = mmbtu * 121;            
        }
        if (unitHm3 == 0) {
            bill = 0;

        } else if (unitHm3 < 0.5 && unitHm3 > 0) {
            mmbtu = unitHm3 * 954 / 281.7385;
            bill = mmbtu * 121;
        } else if (unitHm3 < 1) {
            mmbtu = unitHm3 * 954 / 281.7385;
            bill = mmbtu * 300;

        } else if (units < 2) {
            // if 1.13Hm3 is consumed then for 1 hm use 121.00 and for 0.13Hm3 use 553
            double unitHm31 = unitHm3 - 1;
            double mmbtu1 = 1 * 954 / 281.7385;
            double mmbtu2 = unitHm31 * 954 / 281.7385;
            bill = mmbtu1 * 121 + mmbtu2 * 553;
        } else if (units < 3) {
            // if 2.13Hm3 is consumed then for 2 hm use 554.00 and for 0.13Hm3 use 738
            double unitHm32 = unitHm3 - 2;
            double mmbtu1 = 2 * 954 / 281.7385;
            double mmbtu2 = unitHm32 * 954 / 281.7385;
            bill = mmbtu1 * 553 + mmbtu2 * 738;
        } else if (units < 4) {
            // if 3.13Hm3 is consumed then for 3 hm use 739.00 and for 0.13Hm3 use 1,107
            double unitHm33 = unitHm3 - 3;
            double mmbtu1 = 3 * 954 / 281.7385;
            double mmbtu2 = unitHm33 * 954 / 281.7385;
            bill = mmbtu1 * 738 + mmbtu2 * 1107;
        } else if (units > 4) {
            // if 4.13Hm3 is consumed then for 4 hm use 1,108.00 and for 0.13Hm3 use 1,460
            double unitHm34 = unitHm3 - 4;
            double mmbtu1 = 4 * 954 / 281.7385;
            double mmbtu2 = unitHm34 * 954 / 281.7385;
            bill = mmbtu1 * 1107 + mmbtu2 * 1460;
        } else {
            System.out.println("Invalid units");
            return bill;
        }
        bill = 0.17 * bill;

        return bill;
    }

    public static double water(double units) {

        if (units < 0) {
            if (units >-1000) {
                bill = -400;
            } else {
                bill = -1000;
            }
        }
        
        if (units == 0) {
            bill = 0;
        } else if (units <= 1000 && units > 0) {
            bill = 400;
        } else if (units <= 2000) {
            bill = 1000;
        } else if (units > 2000) {
            bill = 1000 + units * 1.5;
        }
        bill = 0.17 * bill;
        return bill;
    }

    public static double phone(int localPhoneCalls, int internationalPhoneCalls) {
        int local_bill = localPhoneCalls * 5;
        int international_bill = internationalPhoneCalls * 10;
        bill = local_bill + international_bill;
        bill = bill + 0.17 * bill;
        return bill;
    }

    public static double internet(int unitsI) {
        bill = unitsI * 10;
        bill = bill + 0.17 * bill;
        return bill;
        
    }
}
