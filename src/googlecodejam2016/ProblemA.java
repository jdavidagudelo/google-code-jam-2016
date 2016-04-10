/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googlecodejam2016;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author ubidotsjd
 */
public class ProblemA {
    
    
    public boolean hasAllDigits(Set<Integer> set){
        for(int i = 0; i < 10; i++){
            if(!set.contains(i)){
                return false;
            }
        }
        return true;
    }
    public String getMaxValue(int n){
        if(n == 0){
            return "INSOMNIA";
        }
        Set<Integer> digits = new HashSet<>();
        int p = 1;
        int x = n;
        while(true){
            char[] c = (""+(x*p)).toCharArray();
            Integer[] d = new Integer[c.length];
            for(int i = 0; i < c.length; i++){
                d[i] = Integer.parseInt(c[i]+"");
            }
            digits.addAll(Arrays.asList(d));
            if(this.hasAllDigits(digits)){
                return String.valueOf(x*p);
            }
            p++;
        }
    }
     public static void process(String inputPath, String outputPath) throws FileNotFoundException, IOException {
        ProblemA problema = new ProblemA();
         try (BufferedReader reader = new BufferedReader(new FileReader(new File(inputPath)))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputPath)))) {
                String line = reader.readLine();
                while (line != null) {
                    int count = Integer.parseInt(line);
                    line = reader.readLine();
                    for(int i = 0; i < count && line != null; i++){
                        String r = problema.getMaxValue(Integer.parseInt(line));
                        writer.write("Case #"+(i+1)+": "+r+"\n");
                        line = reader.readLine();
                    }
                }
            }
        }
    }
   public static void main(String args[]) throws FileNotFoundException, IOException {
        ClassLoader classLoader = ProblemA.class.getClassLoader();
        String fileLarge = classLoader.getResource("com/googlecodejam/juan/resources/problema/large.in").getFile();
        String fileSmall = classLoader.getResource("com/googlecodejam/juan/resources/problema/small.in").getFile();
        process(fileLarge, "large.txt");
        process(fileSmall, "small.txt");
        ProblemA pa = new ProblemA();
    }
    
}
