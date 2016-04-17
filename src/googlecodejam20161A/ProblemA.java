/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googlecodejam20161A;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author juan
 */
public class ProblemA {
    public String result(String s){
        String result = "";
        int max = 'A';
        for(int i = 0; i < s.length(); i++){
            int x = (int)s.charAt(i);
            if(x < max){
                result = result + (char)x;
            }
            else {
                max = x;
                result = ((char)x) + result;
            }
            
        }
        return result;
    }
    public static void process(String inputPath, String outputPath) throws FileNotFoundException, IOException {
       ProblemA pa = new ProblemA();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(inputPath)))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputPath)))) {
                String line = reader.readLine();
                while (line != null) {
                    int n = Integer.parseInt(line);
                    for(int i = 0; i < n; i++){
                        line = reader.readLine();
                        writer.write("Case #"+(i+1)+": "+pa.result(line)+"\n");
                    }
                    line = reader.readLine();
                }
            }
        }
    }
    public static void main(String args[]) throws FileNotFoundException, IOException {
        ClassLoader classLoader = ProblemA.class.getClassLoader();
        //change it with your specific resources for each input.
        String fileLarge = classLoader.getResource("com/googlecodejam/juan/round1a/problema/large.in").getFile();
        String fileSmall = classLoader.getResource("com/googlecodejam/juan/round1a/problema/small.in").getFile();
        process(fileLarge, "large.txt");
        process(fileSmall, "small.txt");
    }
}
