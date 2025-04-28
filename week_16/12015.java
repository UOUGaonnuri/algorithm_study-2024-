package algorithm_study-2024-.week_16;

public class 12015 {
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

            if (dp.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
