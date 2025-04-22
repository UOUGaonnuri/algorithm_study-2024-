import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr));
    }

    static String solution(int N, int[] arr) {
        List<Integer> dp = new ArrayList<>();
        int[] idxDP = new int[N];

        for (int i = 0; i < N; i++) {
            int idx = find(arr[i], dp);
            if (dp.size() == idx) {
                dp.add(arr[i]);
            }
            dp.set(idx, Math.min(dp.get(idx), arr[i]));
            idxDP[i] = idx;
        }

        int now = dp.size() - 1;

        int[] result = new int[dp.size()];

        for (int i = N - 1; i >= 0 && now >= 0; i--) {
            if (idxDP[i] == now) {
                result[now--] = arr[i];
            }
        }

        return dp.size() + "\n" + Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" "));
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