import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
* 여행 가자
* N개의 도시, M개의 여행 계획에 속한 도시들의 수
* 여행 계획에 속한 도시들이 모두 연결되어 있는지 확인
* 각 도시들의 대표 도시를 찾기
*
 */
public class 1976 {

    static String SUCCESS = "YES";
    static String FAIL = "NO";

    public static void main(String[] args)throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int M = Integer.parseInt(bf.readLine());

        int[][] cities = new int[N + 1][N + 1];
        int[] route = new int[M];

        //도시간 연결 정보 입력
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= N; j++) {
                cities[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //여행 경로 입력
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, M, cities, route));
    }
    static String solution(int N, int M, int[][] cities, int[] route) {
        int[] group = IntStream.range(0, N + 1).toArray();

        // 도시간 연결 처리
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (cities[i][j] == 1) {
                    int a = find(i, group);
                    int b = find(j, group);
                    group[Math.max(a, b)] = Math.min(a, b);
                }
            }
        }

        // 여행 경로 확인
        for (int i = 1; i < M; i++) {
            if (find(route[0], group) != find(route[i], group)) {
                return FAIL;
            }
        }
        return SUCCESS;
    }

    static int find(int n, int[] group) {
        if (n != group[n]) group[n] = find(group[n], group);
        return group[n];
    }
}
