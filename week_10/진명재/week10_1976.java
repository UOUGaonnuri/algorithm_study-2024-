package week_10.진명재;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class week10_1976 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int m = Integer.parseInt(br.readLine());

        int[][] arr = new int[n+1][n+1];
        int[] city = IntStream.rangeClosed(0,n+1).toArray();

        for(int i=1;i<=n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        solution(n,city,arr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int first = find(Integer.parseInt(st.nextToken()), city);
        for (int i = 1; i < m; i++) {
            int k = Integer.parseInt(st.nextToken());
            if (first != find(k, city)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    static void solution(int n, int[] city , int[][] arr){

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(arr[i][j] ==1){
                    int a = find(i,city);
                    int b = find(j,city);

                    city[Math.max(a,b)] = Math.min(a,b);
                }
            }
        }

    }

    static int find(int n, int[] city){
        if (city[n] == n) return n;
        return city[n] = find(city[n], city);
    }
}
