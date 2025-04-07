package week_10.진명재;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class week10_10775 {
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int m = Integer.parseInt(br.readLine());

        int[] arr = new int[m];

        int[] g = IntStream.rangeClosed(0,n+1).toArray();

        cnt = 0;
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(br.readLine());

            if (find(arr[i], g) ==0) {
                System.out.println(cnt);
                return;
            }
            group(arr[i],g);
            cnt++;


        }

        System.out.println(cnt);
    }
    static void group(int n,  int[] g){
        int a = find(n,g);

        g[n] = a - 1;
        while(a!=n) {
            n--;
            g[n] = a - 1;
        }
    }

    static int find(int n,int[]g){
        if(g[n] !=n){
            g[n] = find(g[n],g);
        }
        return g[n];
    }
}
