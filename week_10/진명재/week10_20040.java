package week_10.진명재;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class week10_20040 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(st.nextToken());

        int[] group = IntStream.rangeClosed(0,n+1).toArray();



        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            if(find(f,group) == find(s,group)){
                System.out.println(i);
                return;
            }
            union(f,s,group);
        }
        System.out.println(0);

    }

    static void union(int f, int s , int[] group){
        int a = find(f,group);
        int b = find(s,group);

        group[Math.max(a,b)] = Math.min(a,b);

    }
    static int find(int n,  int[] group){
        if(group[n] !=n){
            group[n] = find(group[n],group);
        }
        return group[n];
    }

}
