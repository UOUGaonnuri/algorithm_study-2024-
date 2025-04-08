import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 11811 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int [][] arr = new int[N][N];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solution(N,arr);
    }

    static void solution(int N , int[][] arr){
        int[] result = new int[N];
        for(int i = 0; i < N; i++){
            for(int j =0; j < N; j++){
                if(i==j) continue;
                result[i] = result[i] | arr[i][j];
            }
        }
        for(int i = 0; i < N; i++) {
            System.out.print(result[i] +" ");
        }
    }
}
