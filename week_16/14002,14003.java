package algorithm_study-2024-.week_16;
/*
* 역추적
* 수열 출력이 추가
*
* idx가 arr[i] 몇 번째 길이(0부터)의 끝에 들어간 원소인지를 나타내니까?
* 각 수열 원소 마다 몇 번째 길이 끝에 들어가는 지 저장해 두고 가장 긴 수열 길이일 때 부터 역으로 복원
* 그러면 idx가 0,1,0,2,1,3 이여도 3,2,1,0으로 복구
* */
public class 14002 , 14003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        StringBuilder sb = new StringBuilder();

        solution(N, arr, sb);
    }

    static void solution(int N, int[] arr, StringBuilder sb) {
        List<Integer> DP = new ArrayList<>();
        int[] idxDP = new int[N];

        for (int i = 0; i < N; i++) {
            int idx = find(arr[i], DP);
            if (idx == DP.size()) {
                DP.add(arr[i]);
                idxDP[i] = idx;
            } else {
                DP.set(idx, arr[i]);
            }
            idxDP[i] = idx;
        }
        sb.append(DP.size()).append("\n");

        int resIdx = DP.size() - 1;
        int[] result = new int[DP.size()];
        for (int i = N - 1; i >= 0 ; i--) {
            if (idxDP[i] == resIdx) {
                result[resIdx--] = arr[i];
            }
        }
        for (int num : result) {
            sb.append(num).append(" ");
        }
        System.out.print(sb);
    }

    static int find(int target, List<Integer> dp) {
        int left = 0;
        int right = dp.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (dp.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}
