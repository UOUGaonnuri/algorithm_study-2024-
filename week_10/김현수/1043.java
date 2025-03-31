import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
* 거짓말
* 진실, 과장된 이야기(거짓말)
* 사람 수 N, 파티 수 M, 진실을 아는 사람 수 K, 사람들 번호
* 진실을 아는 사람이 있는 파티에 참가하면 그 파티에 참가한 모든 사람이 진실을 알게됨
* 모든 파티에 참가한 사람이 진실을 아는 사람이 아니라면 거짓말을 할 수 있음
* 거짓말을 할 수 있는 파티의 최대 개수
*
* - 진실을 아는 사람들을 하나의 그룹으로 묶음
* - 각 파티마다 참석자들을 Union 연산으로 연결
* - 각 파티를 확인하여 진실을 아는 그룹과 연결되지 않은 파티 수를 계산
* */
public class 1043 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        int K = Integer.parseInt(st.nextToken());
        int[] truth = new int[K];
        for (int i = 0; i < K; i++) {
            truth[i] = Integer.parseInt(st.nextToken());
        }

        int[][] parties = new int[M][];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int count = Integer.parseInt(st.nextToken());
            parties[i] = new int[count];
            for (int j = 0; j < count; j++) {
                parties[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(N, M, K, truth, parties));
    }
    static int solution(int N, int M, int K, int[] truth, int[][] parties) {
        int[] group = IntStream.range(0, N + 1).toArray();
        int result = 0;

        // 진실을 아는 사람들을 하나의 그룹으로
        for (int i = 1; i < truth.length; i++) {
            union(truth[0], truth[i], group);
        }

        // 각 파티의 사람들을 연결
        for (int[] party : parties) {
            for (int i = 1; i < party.length; i++) {
                union(party[0], party[i], group);
            }
        }

        // 진실을 아는 사람의 그룹에 속하지 않은 파티 카운트
        for (int[] party : parties) {
            if(truth.length == 0 || find(party[0], group) != find(truth[0], group)) {
                result++;
            }
        }
    return result;
    }

    static int find(int n, int[] group) {
        if (n != group[n]) group[n] = find(group[n], group);
        return group[n];
    }
    static void union(int a, int b, int[] group) {
        a = find(a, group);
        b = find(b, group);
        if (a != b) group[Math.max(a, b)] = Math.min(a, b);
    }
}
