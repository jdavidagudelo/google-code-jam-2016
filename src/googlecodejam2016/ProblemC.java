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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ubidotsjd
 */
public class ProblemC {
    Map<BigInteger, BigInteger> factors = new HashMap<>();
    List<BigInteger> primes = new ArrayList<>();
    public class Result{
        BigInteger decimalValue;
        BigInteger[] divisors = new BigInteger[9];
        public Result(BigInteger decimalValue, BigInteger[] divisors){
            this.decimalValue=decimalValue;
            this.divisors = divisors;
        }
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(decimalValue).append(" ");
            for(BigInteger l: divisors){
                sb.append(l.toString()).append(" ");
            }
            return sb.toString().trim();
        }
    }
    public char[] convertToBase(int x, int base, int count){
        BigInteger b = new BigInteger(String.valueOf(x));
        String test = b.toString(base);
        String s = "";
        for(int i = test.length(); i < count; i++){
            s+="0";
        }
        return (s+test).toCharArray();
    }
    public BigInteger getBase(char[] x, int base){
        return new BigInteger(new String(x), base);
    }
    public BigInteger getFactor(BigInteger x){
        if(x.isProbablePrime(30)){
            factors.put(x, BigInteger.ONE);
            return BigInteger.ONE;
        }
        if(factors.containsKey(x)){
            return factors.get(x);
        }
        for(BigInteger prime : primes){
            if(x.mod(prime).equals(BigInteger.ZERO)){
                factors.put(x, prime);
                return prime;
            }
        }
        /*for(BigInteger i = BigInteger.valueOf(2); i.multiply(i).compareTo(x) <= 0; i=i.add(BigInteger.ONE)){
            if(x.mod(i).equals(BigInteger.ZERO)){
                factors.put(x, i);
                return i;
            }
        }*/
        factors.put(x, BigInteger.ONE);
        return BigInteger.ONE;
    }
    public Result getValid(char[] x){
        boolean valid = true;
        BigInteger decimalValue = BigInteger.ZERO;
        BigInteger[] divisors = new BigInteger[9];
        for(int i = 2; i <= 10; i++){
            BigInteger test = getBase(x, i);
            divisors[i-2] = getFactor(test);
            if(divisors[i-2].equals(BigInteger.ONE)){
                valid = false;
            }
            if(i == 10){
                decimalValue = test;
            }
        }
        if(valid){
            return new Result(decimalValue, divisors);
        }
        return null;
    }
    public void updatePrimes(BigInteger maxValue){
        for(BigInteger i = BigInteger.valueOf(2); i.compareTo(maxValue) <= 0; i = i.add(BigInteger.ONE)){
            if(i.isProbablePrime(30)){
                primes.add(i);
            }
        }
    }
    public Result testResult(int i, int n){
        char[] x = convertToBase(i, 2, n-2);
        char[] a = new char[n];
        a[0] = a[a.length-1] = '1';
        for(int l = 1; l < a.length-1; l++){
            a[l] = x[l-1];
        }
        BigInteger test = new BigInteger(new String(a));
        if(test.isProbablePrime(30)){
            return null;
        }
        Result r = getValid(a);
        if(r != null){
            return r;
        }
        return null;
    }
    public List<Result> getResults(int n, int j){
        updatePrimes(BigInteger.valueOf(1000000l));
        List<Result> result = new ArrayList<>();
        int k = 0;
        for(int i = (1 << (n-2)) - 1; i >= 0; i--){
            Result r = testResult(i, n);
            if(r != null){
                result.add(r);
                k++;
            }
            if(k == j){
                return result;
            }
        }
        return result;
    }
    
    public static void process(String inputPath, String outputPath) throws FileNotFoundException, IOException {
        ProblemC pc = new ProblemC();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(inputPath)))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputPath)))) {
                String line = reader.readLine();
                while (line != null) {
                    int t = Integer.parseInt(line);
                    for(int i = 0; i < t; i++){
                        line = reader.readLine();
                        String s[] = line.split(" ");
                        int n = Integer.parseInt(s[0]);
                        int j = Integer.parseInt(s[1]);
                        long time = System.currentTimeMillis();
                        StringBuilder sb = new StringBuilder();
                        sb.append("Case #").append(i+1).append(":\n");
                        List<Result> results = pc.getResults(n, j);
                        results.stream().forEach((result) -> {
                            sb.append(result.toString()).append("\n");
                        });
                        writer.write(sb.toString());
                    }
                    line = reader.readLine();
                }
            }
        }
    }
    public static void main(String args[]) throws FileNotFoundException, IOException {
        ClassLoader classLoader = ProblemC.class.getClassLoader();
        //change it with your specific resources for each input.
       String fileLarge = classLoader.getResource("com/googlecodejam/juan/resources/problemc/large.in").getFile();
        //String fileSmall = classLoader.getResource("com/googlecodejam/juan/resources/problemc/small.in").getFile();
        process(fileLarge, "large.txt");
        //process(fileSmall, "small.txt");
       
    }
}
