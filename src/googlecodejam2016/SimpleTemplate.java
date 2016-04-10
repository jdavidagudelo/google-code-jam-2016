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

/**
 *
 * @author juan
 */
public class SimpleTemplate {
    public static void process(String inputPath, String outputPath) throws FileNotFoundException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(inputPath)))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputPath)))) {
                String line = reader.readLine();
                while (line != null) {
                    //Here you read and write everything, the reader and writer are automatically closed by java 7
                }
            }
        }
    }
    public static void main(String args[]) throws FileNotFoundException, IOException {
        ClassLoader classLoader = SimpleTemplate.class.getClassLoader();
        //change it with your specific resources for each input.
        String fileLarge = classLoader.getResource("com/google/code/jam/juan/allyourbase/large.in").getFile();
        String fileSmall = classLoader.getResource("com/google/code/jam/juan/allyourbase/small.in").getFile();
        process(fileLarge, "large.txt");
        process(fileSmall, "small.txt");
    }
}
