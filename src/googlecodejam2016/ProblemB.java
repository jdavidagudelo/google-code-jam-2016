/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googlecodejam2016;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author ubidotsjd
 */
public class ProblemB {
    public class Node implements Comparable<Node>{
        int cost;
        int omit;
        BigInteger key;
        Integer[] bits;
        public Node(int cost, Integer[] bits, BigInteger key, int omit){
            this.cost = cost;
            this.bits = bits;
            this.key = key;
            this.omit = omit;
        }

        @Override
        public int compareTo(Node o) {
            if(Integer.compare(omit, o.omit) != 0){
                return Integer.compare(o.omit, omit);
            }
            if(Integer.compare(cost, o.cost) != 0){
                return Integer.compare(cost, o.cost);
            }
            return key.compareTo(o.key);
        }
    }
    public boolean isOver(Integer[] bits){
        for(int x : bits){
            if(x == 0){
                return false;
            }
        }
        return true;
    }
    public Integer[] flip(Integer bits[], int i){
        int k = 0;
        Integer r[] = new Integer[bits.length];
        for(int x = i - 1; x >= 0; x--){
            r[k] = bits[x] == 0? 1 : 0;
            k++;
        }
        for(int x = i; x < bits.length; x++){
            r[k] = bits[x];
            k++;
        }
        return r;
    }
    private BigInteger[] pows = {};
    public BigInteger decode(Integer[] bits){
        BigInteger r = BigInteger.ZERO;
        int k = 0;
        for(int i = bits.length-1; i >= 0; i--){
            r = r.add(BigInteger.valueOf(bits[i]).multiply(pows[k]));
            k++;
        }
        return r;
    }
    public int getCount(String x){
        int n = x.length();
        pows = new BigInteger[n];
        for(int i = 0; i < n; i++){
            pows[i] = BigInteger.valueOf(2).pow(i);
        }
        Integer b[] = new Integer[x.length()];
        for(int i = 0; i  < x.length(); i++){
            b[i] = x.charAt(i) == '+'? 1 : 0;
        }
        Set<Integer[]> visited = new HashSet<>();
        TreeSet<Node> queue = new TreeSet<>();
        int omit = 0;
        for(int i = b.length-1; i >= 0 && b[i] == 1;  i--){
            omit++;
        }
        queue.add(new Node(0, b, decode(b), omit));
        while(!queue.isEmpty()){
            Node node = queue.pollFirst();
            if(isOver(node.bits)){
                return node.cost;
            }
            if(visited.contains(node.bits)){
                continue;
            }
            visited.add(node.bits);
            omit = 0;
            for(int i = node.bits.length-1; i >= 0 && node.bits[i] == 1;  i--){
                omit++;
            }
            for(int i = 1; i <= n-omit; i++){
                Integer[] bits = flip(node.bits, i);
                omit = 0;
                for(int k = bits.length-1; k >= 0 && bits[k] == 1;  k--){
                    omit++;
                }
                BigInteger key = decode(bits);
                int cost = node.cost + 1;
                if(!visited.contains(bits)){
                    queue.add(new Node(cost, bits, key, omit));
                }
            }
        }
        return 0;
    }
       public static void process(String inputPath, String outputPath) throws FileNotFoundException, IOException {
        ProblemB pb = new ProblemB();
           try (BufferedReader reader = new BufferedReader(new FileReader(new File(inputPath)))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputPath)))) {
                String line = reader.readLine();
                while (line != null) {
                    int count = Integer.parseInt(line);
                    line = reader.readLine();
                    for(int i = 0; i < count; i++){
                        String test = line;
                        int r = pb.getCount(test);
                        writer.write("Case #"+(i+1)+": "+r+"\n");
                        line = reader.readLine();
                    }
                    line = reader.readLine();
                }
            }
        }
    }
    public static void main(String args[]) throws FileNotFoundException, IOException {
        ClassLoader classLoader = ProblemB.class.getClassLoader();
        //change it with your specific resources for each input.
        String fileLarge = classLoader.getResource("com/googlecodejam/juan/resources/problemb/large.in").getFile();
        String fileSmall = classLoader.getResource("com/googlecodejam/juan/resources/problemb/small.in").getFile();
        process(fileLarge, "large.txt");
        process(fileSmall, "small.txt");
        /**Random r = new Random();
        ProblemB pb = new ProblemB();
        for(int k = 0; k <  1000; k++){
            char[] c = new char[100];
            System.out.println(k);
            for(int i = 0; i < c.length; i++){
                c[i] = r.nextDouble() >= 0.5? '+': '-';
            }
            System.out.println(pb.getCount(new String(c)));
        }*/
    }
}
