/*
N명의 사람과 N개의 일
모든 사람 -> 모든 일 가능
일 하나당 한 사람
모든 사람이 각자 다른 일을 담당해야한다
일 : 1 - N 번호
사람 : 1 - N 번호

일 -> 사람, 사람 -> 일 매칭

입력
N이  <= 20
비용은 1만이하

출력
최대 20만 int가능하죠?
비용의 최소값

사람 하나에 일 하나

20명한테 20개의 일을 각각 부여해보자
20 ^ 20
if 최솟값인지
    최솟값이면 업데이트
for 사람
    for 일
        이전에 시키지않은 일인지
        dfs()


중복을 빼요
20! 8 4만
팩토리얼, 조합 -> 머릿속으로 암산안되면 안되는거

N = 4일때
1 - 1
2 - 2
3 - 3
4 - 4

1 - 1
2 - 2
3 - 4
4 - 3

...

1 - 2
2 - 1
3 - 3
4 - 4

1 - 2
2 - 1
3 - 4
4 - 3

앞에서 1, 2 - 1, 2 -> 그러면 뒤에서 나올 개수 몇개인지

밑에 N개 남을때마다 N! -> 중복이 N!

bits, person 같으면 같은 결과 -> 중복
DP
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] map = new int[N][];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.print(solution(N, map));
    }

    static int solution(int N, int[][] costs) {
        return dfs(N, costs, 0, 0, new int[N][1 << N], new boolean[N][1 << N]);
    }

    // 1 - 1, 2 - 2
    // 1 - 2, 2 - 1
    // bits = 3(1 + 2), person = 3
    // dfs(N, cost, 3, 3) => 똑같이 실행
    // 1 << 1 10 = 2 | 1 << 2 100 = 4 -> 110 => 6
    // 최대 N번째 비트까지 사용한다하면 1<<N;
    static int dfs(int N, int[][] cost, int bits, int person, int[][] dp, boolean[][] visited) {
        if (N == person) {
            return 0;
        }

        if (visited[person][bits]) {
            return dp[person][bits];
        }

        visited[person][bits] = true;

        int result = 1_000_000;

        for (int i = 0; i < N; i++) {
            if ((bits & (1 << i)) > 0) continue;
            result = Math.min(result,
                    cost[person][i] + dfs(N, cost, bits | (1 << i), person + 1, dp, visited)
            );
        }

        dp[person][bits] = result;

        return result;
    }
}