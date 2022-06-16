/*
@author Mustafa Bulut
@since 05.01.2021
 */
import java.io.*;

import java.util.*;


public class NgramExtractor {

    static String input;
    static String output;
    static String n;





    public static void main(String[] args) {
        StringBuilder strBuilder = new StringBuilder();
        String[] myArr;

        input = args[0];
        output = args[1];
        n = args[2];
        int n = Integer.parseInt(NgramExtractor.n);


        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line = br.readLine();

            while (line != null) {
                strBuilder.append(line + " ");
                strBuilder.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = strBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String x = strBuilder.toString();
        int count = 0;

        myArr = x.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        for (int i = 0; i < myArr.length; i++) {
            for (int j = 0; j < myArr.length; j++) {
                if (myArr[i].equals(myArr[j])) {
                    count++;
                }

            }
        }

        int count2 = 0;
        HashMap<String, Integer> hashMap = new HashMap<>(count);
        ArrayList<String> myArrL3 = new ArrayList<>();

        myArrL3 = nGrams(n, myArr);


        for (int i = 0; i < myArrL3.size(); i++) {
            for (int j = 0; j < myArrL3.size(); j++) {
                if (myArr[i].equals(myArr[j])) {
                    count2++;
                    hashMap.put(myArrL3.get(j), count2);

                }
            }
            count2 = 0;

        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output));) {

            for (String name : hashMap.keySet()) {
                int value = hashMap.get(name);


                writer.write(name + "," + value + "," + Frequency(value, myArr.length));
                writer.write("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }




}
    public static String Frequency(int count, int total) {
        Float result = (float)100*count/total;
        return String.valueOf(result);
    }
    public static ArrayList<String> nGrams(int n, String[] myArr1) {
        ArrayList<String> myArrL2 = new ArrayList<String>();

        for(int i=0; i<myArr1.length-n; i++) {
            String nGram = "";
            for(int j=i; j<i+n; j++)
                nGram += myArr1[j] + " ";
            nGram = nGram.trim();
            myArrL2.add(nGram);
        }
        return myArrL2;
    }



}

