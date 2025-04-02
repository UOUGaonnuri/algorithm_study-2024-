import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 14400 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] x = new int[N];
        int[] y = new int[N];

        int m = N / 2;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(x, y, m));
    }

    static long solution(int[] x, int[] y, int m) {
        Arrays.sort(x);
        Arrays.sort(y);

        long result = 0;
        for (int i = 0; i < x.length; i++) {
            result += Math.abs(x[m] - x[i]) + Math.abs(y[m] - y[i]);
        }

        return result;
    }
}
