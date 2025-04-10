
/*
* 할 일 정하기 1
*
* N명의 사람과 N개의 일, 각 사람은 일을 하나 담당, 각 일을 담당하는 사람 한명.
*모든 일을 하는데 필요한 비용의 최솟값을 구하는 문제
*
* 입력:  N (1 ≤ N ≤ 20), D 내용
* 출력: 모든 일을 하는데 필요한 비용의 최솟값
*
*완탐?(DFS) -> 사람 N명 × 일 N개 → N! , 중복 제거(비트마스크) -> 아직 중복 많다..
* -> DP (중복 상태 저장)-Top-down방식
*
 * - DFS로 사람마다 일을 할당
 * - 비트마스크로 상태 확인
 * - DP로 중복 계산 방지
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 1311 {

    static int[] DP;
    static int[][] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        cost = new int[N][N];
        DP = new int[1 << N];
        Arrays.fill(DP, -1); // -1로 초기화해서 방문 여부 구분

        for (int i = 0; i < N; i++) {
            cost[i] = Arrays.stream(bf.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        System.out.println(solution(N));
    }

    static int solution(int N) {
        return dfs(N, 0, 0); // now=0 (현재 사람), bits=0 (아직 일 없음)
    }

    static int dfs(int N, int bits, int now) {
        if (now == N) return 0; // 모든 사람에게 일을 할당

        if (DP[bits] != -1) return DP[bits]; // 이미 계산

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            if ((bits & (1 << i)) == 0) { // i번 일이 아직 할당x
                int next = dfs(N, bits | (1 << i), now + 1) + cost[now][i];
                min = Math.min(min, next);
            }
        }

        return DP[bits] = min;
    }
}
