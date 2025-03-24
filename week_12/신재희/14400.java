import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] x = new int[N];
        int[] y = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            x[i] = Integer.parseInt(input.nextToken());
            y[i] = Integer.parseInt(input.nextToken());
        }

        System.out.println(solution(N, x, y));
    }

    public static long solution(int N, int[] x, int[] y) {
        Arrays.sort(x);
        Arrays.sort(y);

        long result = 0;

        int a = x[N >> 1];
        int b = y[N >> 1];

        for (int i = 0; i < N; i++) {
            result += Math.abs(x[i] - a) + Math.abs(y[i] - b);
        }

        return result;
    }
}