/*
///////////////////////////////////////////////////////////////////
NOTR:
THIS METHOD IS ONLY USED TO GENERATE NEW CONSUMER IDS ENTRIES AND/OR FILES 
TO BE USED IN THE TESTING OF THE PROGRAM.
THE METHOD IS NOT USED IN THE ACTUAL PROGRAM.
PREVIOUS FILE SHOULD EXIST BEFORE ACTUALLY RUNNING THIS METHOD.
///////////////////////////////////////////////////////////////////
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class client_RecGenerator {
    public static String generateRandomPassword(int len) {
        String chars = "abcdefghijk"
                + "lmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        // capitilize the first letter
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        String cid = "";
        String block = "";
        String subBlock[] = { "1", "2", "3", "4" };

        // String cidArray[] = new String[24000];
        // each block has 4 sub-blocks and each subblock has 10 streets and 20 houses
        // and each wih 3 portions
        String blocks[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" }; // A to J
        String streets[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }; // 0 to 9
        String houses[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
                "16",
                "17", "18", "19", "20" }; // 0 to 20
        String portions[] = { "1", "2", "3" }; // 1 to 3

        FileReader fr = new FileReader("names.txt");
        BufferedReader br = new BufferedReader(fr);
        File f = new File("clientRec.txt");

        for (int i = 0; i < blocks.length; i++) {
            block = blocks[i];
            for (int j = 0; j < subBlock.length; j++) {
                for (int k = 0; k < streets.length; k++) {
                    for (int l = 0; l < houses.length; l++) {
                        for (int m = 0; m < portions.length; m++) {
                            cid = block + subBlock[j] + streets[k] + houses[l] + portions[m];

                            FileWriter fw = new FileWriter(f, true);
                            BufferedWriter bw = new BufferedWriter(fw);

                            String name = br.readLine();

                            bw.write(cid + "," + name);

                            bw.newLine();
                            bw.close();

                        }
                    }
                }
            }
        }
    }

}
