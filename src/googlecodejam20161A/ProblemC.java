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
import java.util.*;

/**
 *
 * @author juan
 */
public class ProblemC {
    public class Node implements Comparable<Node>{
        int i;
        int cost;
        public Node(int i, int cost){
            this.i = i;
            this.cost = cost;
        }
        public int compareTo(Node n){
            if(Integer.compare(cost, n.cost) != 0){
                return Integer.compare(n.cost, cost);
            }
            return Integer.compare(i, n.i);
        }
    }
    public int maxCircle(int f[]){
       System.out.println(Arrays.toString(f));
        int max = 0;
        for(int i = 0; i < f.length; i++){
            TreeSet<Node>  queue = new TreeSet<>();
            queue.add(new Node(i, 1));
            boolean visited[] = new boolean[f.length];
            while(!queue.isEmpty()){
                Node current = queue.pollFirst();
                System.out.println(current.i);
                if(visited[current.i]){
                    continue;
                }
                visited[current.i] = true;
                if(!visited[f[current.i]]){
                    Node next = new Node(f[current.i], current.cost+1);
                    queue.add(next);
                }
                else{
                    max = Math.max(max, current.cost);
                }
            }
        }
        return max;
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
                        int n = Integer.parseInt(line);
                        int f[] = new int[n];
                        String[] split = reader.readLine().split(" ");
                        for(int j = 0; j < n; j++){
                            f[j] = Integer.parseInt(split[j]) - 1;
                        }
                        writer.write("Case #"+(i+1)+": "+pc.maxCircle(f)+"\n");
                    }
                    line = reader.readLine();
                }
            }
        }
    }
    public static void main(String args[]) throws FileNotFoundException, IOException {
        ClassLoader classLoader = ProblemC.class.getClassLoader();
        //change it with your specific resources for each input.
       // String fileLarge = classLoader.getResource("com/googlecodejam/juan/round1a/problemc/large.in").getFile();
        String fileSmall = classLoader.getResource("com/googlecodejam/juan/round1a/problemc/small.in").getFile();
        //process(fileLarge, "large.txt");
        process(fileSmall, "small.txt");
    }
}
