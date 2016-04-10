/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googlecodejam2016;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ubidotsjd
 */
public class GeneratePrimes {
    public static void main(String args[]) throws IOException{
        String outputPath = "primes.txt";
        List<Long> primes = new ArrayList<>();
        BigInteger max = new BigInteger("1111111111111111111111111111111");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputPath)))) {
               for(BigInteger i = BigInteger.valueOf(2); i.compareTo(max) <= 0; i=i.add(BigInteger.ONE)){
                   System.out.println(i);
               }
        }
    }
}
