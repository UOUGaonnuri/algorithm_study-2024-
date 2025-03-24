import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] x = new int[N];

        StringTokenizer input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(input.nextToken());
        }

        System.out.println(solution(N, x));
    }

    public static long solution(int N, int[] x) {
        Arrays.sort(x);

        return x[(N >> 1) - (N & 1 ^ 1)];
    }
}