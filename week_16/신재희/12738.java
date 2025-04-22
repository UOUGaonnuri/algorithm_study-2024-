import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[] arr) {
        List<Integer> dp = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int idx = find(arr[i], dp);
            if (dp.size() == idx) {
                dp.add(arr[i]);
            }
            dp.set(idx, Math.min(dp.get(idx), arr[i]));
        }

        return dp.size();
    }

    static int find(int n, List<Integer> dp) {
        if (dp.isEmpty()) return 0;
        if (dp.get(0) >= n) return 0;
        if (dp.get(dp.size() - 1) < n) return dp.size();

        int s = 0;
        int e = dp.size() - 1;

        while (s + 1 < e) {
            int m = (s + e) >> 1;

            if (dp.get(m) < n) {
                s = m;
            } else {
                e = m;
            }
        }

        return e;
    }
}