package algorithm_study-2024-.week_16;

public class 11055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(br.readLine());
        var arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[] arr) {
        int[] dp = new int[N];
        int max = 0;

        for (int i = 0; i < N; i++) {
            dp[i] = arr[i]; // 자기 자신
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
