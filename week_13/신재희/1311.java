import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var arr = new int[N][];

        DP = new int[1<<N];

        DP[(1<<N)-1] = 1;

        for (int i=0; i<N; i++) {
            arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[][] arr) {
        return dfs(N, arr, 0, 0);
    }

    static int dfs(int N, int[][] arr, int bits, int now) {
        if (now == N) {
            return 0;
        } else if (DP[bits]>0) {
            return DP[bits];
        }

        DP[bits] = Integer.MAX_VALUE;

        for (int i=0; i<N; i++) {
            if (((1<<i)&bits)==0) {
                DP[bits] = Math.min(DP[bits], dfs(N, arr, bits|(1<<i), now+1) + arr[i][now]);
            }
        }

        return DP[bits];
    }
}