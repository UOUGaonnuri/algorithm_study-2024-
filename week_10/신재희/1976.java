import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int M = Integer.parseInt(bf.readLine());

        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        int[] tour = new int[M];
        StringTokenizer input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < M; i++) {
            tour[i] = Integer.parseInt(input.nextToken());
        }

        System.out.println(solution(N, M, arr, tour));
    }

    static String solution(int N, int M, int[][] arr, int[] tour) {
        int[] g = IntStream.range(0, N + 1).toArray();

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (arr[i][j] == 1) {
                    int a = find(i + 1, g);
                    int b = find(j + 1, g);

                    g[Math.max(a, b)] = Math.min(a, b);
                }
            }
        }

        int ng = find(tour[0], g);

        for (int i = 1; i < M; i++) {
            if (ng != find(tour[i], g)) return "NO";
        }

        return "YES";
    }

    static int find(int n, int[] g) {
        if (g[n] == n) return n;
        return g[n] = find(g[n], g);
    }
}