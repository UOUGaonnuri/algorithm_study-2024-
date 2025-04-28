package algorithm_study-2024-.week_16;

public class 11722 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(br.readLine());
        var arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[] arr) {
        var dp = new ArrayList<Integer>();

        for (var num : arr) {
            var idx = find(num, dp);
            if (idx == dp.size()) {
                dp.add(num);
            } else {
                dp.set(idx, num);
            }
        }

        return dp.size();
    }

    static int find(int target, List<Integer> dp) {
        var left = 0;
        var right = dp.size() - 1;

        while (left <= right) {
            var mid = (left + right) / 2;

            if (dp.get(mid) > target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 11722 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[] arr) {
        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, dp[i]);
        }
        return answer;
    }
}
