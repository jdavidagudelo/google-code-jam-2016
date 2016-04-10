/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googlecodejam2016;

import java.math.BigInteger;
import java.util.Arrays;

/**
 *
 * @author ubidotsjd
 */
public class TestProblemC {
    
    public int[] result(int n, int x){
        BigInteger b = BigInteger.valueOf(x);
        String s = b.toString(2);
        int[] r = new int[n];
        int c = 0;
        for(int i = s.length(); i < n; i++){
            r[c] = 0;
            c++;
        }
        for(char ch : s.toCharArray()){
            r[c] = Integer.parseInt(ch+"");
            c++;
        }
        return r;
    }
    public int[] complexity(int[] x, int[] x1, int c, int k){
        if(c == 1){
            return x;
        }
        return next(complexity(x, x1, c-1, k), x1, k);
    }
    public int[] next(int[] x, int[] x1, int k){
        int r[] = new int[k*x.length];
        int c = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < x.length; i++){
            if(x[i] == 1){
                for(int ch : x1){
                    r[c] = ch;
                    c++;
                }
                sb.append(x1);
            }
            else{
                for(int j = 0; j < k; j++){
                    r[c] = 0;
                    c++;
                }
            }
        }
        return r;
    }
    public static void main(String args[]){
        ProblemD pd = new ProblemD();
        for(int k = 2; k <= 6; k++){
            for(int c = 1; c <= 5; c++){
        int rowSum[] = new int[1<<k];
        int columnSum[] = new int[(int)Math.pow(k, c)];
        for(int i = 0; i < (1 << k); i++){
            int[] r = pd.complexity(pd.result(k, i), pd.result(k, i), c,  k);
            int rs = 0;
            for(int j = 0; j < r.length; j++){
                rs += r[j];
                columnSum[j] += r[j];
            }
            rowSum[i] = rs;
        }
        int h = 0;
        for(int x : columnSum){
            h += x;
        }
        System.out.println(Arrays.toString(columnSum)+", "+h);
            }
            System.out.println("############################################################");
        }
    }
}
