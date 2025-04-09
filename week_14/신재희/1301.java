import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static long[][][][][][][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

       var N = Integer.parseInt(bf.readLine());

       var balls = new int[5];

       for (int i=0; i<N; i++) {
           balls[i] = Integer.parseInt(bf.readLine());
       }

        System.out.println(solution(N, balls));
    }


    static long solution(int N, int[] balls) {
        var sum = sum(balls);
        DP = new long[N+1][N+1][balls[0]+1][balls[1]+1][balls[2]+1][balls[3]+1][balls[4]+1];
        return dfs(N, balls, sum, 0, N, N);
    }

    static int sum(int[] balls) {
        var result = 0;
        for (int i: balls) {
            result += i;
        }
        return result;
    }

    static long dfs(int N, int[] balls, int sum, int now, int pprev, int prev) {
        if (sum == now) return 1;

        if (DP[pprev][prev][balls[0]][balls[1]][balls[2]][balls[3]][balls[4]]>0) {
            return DP[pprev][prev][balls[0]][balls[1]][balls[2]][balls[3]][balls[4]];
        }
        if (DP[pprev][prev][balls[0]][balls[1]][balls[2]][balls[3]][balls[4]]==-1) {
            return 0;
        }


        var result = 0L;

        for (int i=0; i<N; i++) {
            if (pprev==i || prev==i) continue;
            if (balls[i]==0) continue;
            balls[i]--;
            result += dfs(N, balls, sum, now+1, prev, i);
            balls[i]++;

        }

        if (result==0) {
            DP[pprev][prev][balls[0]][balls[1]][balls[2]][balls[3]][balls[4]] = -1;
            return 0;
        }

        DP[pprev][prev][balls[0]][balls[1]][balls[2]][balls[3]][balls[4]] = result;

        return DP[pprev][prev][balls[0]][balls[1]][balls[2]][balls[3]][balls[4]];
    }
}