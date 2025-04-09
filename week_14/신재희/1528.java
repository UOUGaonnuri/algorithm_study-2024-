import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    static String solution(int N) {

        if (N == 0) return "0";
        if (N < 0) return "-1";

        var dp = new int[N+1];
        dp[0] = 1;

        var nums = new ArrayList<Integer>();
        find(nums, 0, 0);
        nums.sort(Comparator.naturalOrder());

        for (int num: nums) {
            for (int i=0; i<N; i++) {
                if (dp[i] == 0 || i+num > N) continue;
                if (dp[i+num] == 0 || dp[i+num] > dp[i] + 1) dp[i+num] = dp[i] + 1;
            }
        }

        if (dp[N] == 0) return "-1";

        var result = new ArrayList<Integer>();

        var now = N;
        for (int num: nums) {
            while (now>=num && dp[now] == dp[now-num] + 1) {
                now-=num;
                result.add(num);
            }
        }

        return result.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    static void find(List<Integer> nums, int now, int cnt) {
        if (cnt == 7) return;
        var v1 = now*10+4;
        var v2 = now*10+7;
        nums.add(v1);
        nums.add(v2);
        find(nums, v1, cnt+1);
        find(nums, v2, cnt+1);
    }
}