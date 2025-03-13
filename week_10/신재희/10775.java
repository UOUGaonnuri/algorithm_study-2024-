import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int M = Integer.parseInt(bf.readLine());

        int[] arr = new int[M];

        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        System.out.println(solution(N, M, arr));
    }

    static int solution(int N, int M, int[] arr) {
        int[] gate = IntStream.range(0, N + 1).toArray();

        int count = 0;

        for (int n : arr) {
            int now = find(n, gate);

            if (now == 0) {
                break;
            }

            gate[now] = now - 1;
            count++;
        }

        return count;
    }

    static int find(int n, int[] g) {
        if (g[n] == n) return n;
        return g[n] = find(g[n], g);
    }
}