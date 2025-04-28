package algorithm_study-2024-.week_16;

public class 11054 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(solution(N, arr));
    }
    static int solution(int N, int[] arr) {
        int[] dp1 = new int[N];
        int[] dp2 = new int[N];

        //증가
        for (int i = 0; i < N; i++) {
            int now = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    now = Math.max(now, dp1[j] + 1);
                }
            }
            dp1[i] = now;
        }

        // 감소
        for (int i = N - 1; i >= 0; i--) {
            int now = 1;
            for (int j = N - 1; j > i; j--) {
                if (arr[j] < arr[i]) {
                    now = Math.max(now, dp2[j] + 1);
                }
            }
            dp2[i] = now;
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, dp1[i] + dp2[i] -1 );// i 번 쨰 한번 빼줘야 함(중복 삭제)
        }

        return answer;
    }
}
