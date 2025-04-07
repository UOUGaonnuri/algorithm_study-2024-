
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
* 네트워크 연결
*  컴퓨터 N대, 연결할 수 있는 선 M개, 각 선의 비용
* 모든 컴퓨터를 연결하는데 필요한 최소 비용
*
* - 최소 스패닝 트리, 크루스칼 알고리즘
* - 유니온 파인드, 사이클 확인
* - 간선을 비용순으로 정렬
* - 간선을 하나씩 선택하면서 사이클이 생기지 않는 경우 선택
* - 모든 간선을 선택할 때까지 반복
* - 선택된 간선의 비용의 합이 최소 비용
* */
public class 1922 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int M = Integer.parseInt(bf.readLine());

        int[][] lines = new int[M][3];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
            lines[i][2] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(N, M, lines));

    }
    static int solution(int N, int M, int[][] lines){
        // 비용 기준 오름차순 정렬
        Arrays.sort(lines, (a, b) -> a[2] - b[2]);

        int[] group = new int[N+1];
        for (int i = 1; i <= N; i++) {
            group[i] = i;
        }

        int sum = 0;
        int connected = 0;
        for (int[] line : lines) {
            int a = find(line[0], group);
            int b = find(line[1], group);

            if (a != b) {
                union(a, b, group);
                sum += line[2];
                connected++;

                // 모든 컴퓨터가 연결되면 종료
                if (connected == N - 1) break;
            }
        }
        return sum;
    }
    static int find(int n, int[] group){
        if(n != group[n]) group[n] = find(group[n], group);
        return group[n];
    }
    static void union(int a, int b, int[] group) {
        a = find(a, group);
        b = find(b, group);
        if (a != b) group[Math.max(a, b)] = Math.min(a, b);
    }
}