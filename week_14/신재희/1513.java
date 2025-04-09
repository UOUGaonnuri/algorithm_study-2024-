import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(bf.readLine());

        var N = Integer.parseInt(st.nextToken());
        var M = Integer.parseInt(st.nextToken());
        var C = Integer.parseInt(st.nextToken());

        var map = new int[N][M];

        for (int c = 1; c <= C; c++) {
            var input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            map[input[0]-1][input[1]-1] = c;
        }

        System.out.println(solution(N, M, C, map));
    }

    static String solution(int N, int M, int C, int[][] map) {
        var dp = new int[C + 1][C + 1][N][M]; // 지나간 거 개수, 마지막, x, y

        if (map[0][0] > 0) {
            dp[1][map[0][0]][0][0] = 1;
        } else {
            dp[0][0][0][0] = 1;
        }

        for (int c = 0; c <= C; c++) { // 지나간거 개수 0은 없는거
            for (int last = 0; last <= C; last++) { // 마지막 지나간거
                for (int x=0; x<N; x++) {
                    for (int y=0; y<M; y++) {
                        if (x==0 && y==0) continue;
                        var now = 0;
                        if (x>0) now += dp[c][last][x-1][y];
                        now%=1000007;
                        if (y>0) now += dp[c][last][x][y-1];
                        now%=1000007;
                        if (map[x][y] > last && c!=C) {
                            dp[c + 1][map[x][y]][x][y] += now;
                            dp[c + 1][map[x][y]][x][y]%=1000007;
                        } else if (map[x][y] == 0) {
                            dp[c][last][x][y] += now;
                            dp[c][last][x][y]%=1000007;
                        }

                    }
                }
            }
        }

        var result = new StringJoiner(" ");
        for (int c=0; c<=C; c++) {
            var now = 0;
            for (int i=0; i<=C; i++) {
                now += dp[c][i][N-1][M-1];
                now%=1000007;
            }
            result.add(String.valueOf(now));
        }

        return result.toString();
    }
}