
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 고속철도 설계하기 (스페셜 저지)
 * N개의 도시, 도시 간 설치 비용이 주어진 인접행렬
 * 일부 고속철도는 이미 설치되어 있음(음수로 표시)
 * 추가 설치 비용을 최소화하여 모든 도시가 연결되도록 설계
 *
 * - 최소 스패닝 트리, 크루스칼 알고리즘
 * - 유니온 파인드, 사이클 확인
 * - 이미 설치된 도로는 union 처리
 * - 나머지 간선은 비용순 정렬
 * - 하나씩 선택하면서 사이클이 생기지 않으면 연결
 * - 최종 비용과 설치 간선 정보 출력
 * */
public class 1388 {
    static class Edge {
        int from, to, cost;
        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] costs = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(N, costs));
    }

    static String solution(int N, int[][] costs) {
        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        List<Edge> edges = new ArrayList<>();
        int totalCost = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                int cost = costs[i][j];
                if (cost < 0) {
                    union(i, j, parent); // 이미 설치된 도로는 바로 union
                    totalCost += (-cost); // 이미 설치된 고속철도 비용 더하기
                } else {
                    edges.add(new Edge(i, j, cost));
                }
            }
        }

        edges.sort(Comparator.comparingInt(e -> e.cost));

        List<Edge> installed = new ArrayList<>();

        for (Edge edge : edges) {
            if (find(edge.from, parent) != find(edge.to, parent)) {
                union(edge.from, edge.to, parent);
                totalCost += edge.cost;
                installed.add(edge);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(totalCost).append(" ").append(installed.size()).append("\n");
        for (Edge e : installed) {
            sb.append(e.from).append(" ").append(e.to).append("\n");
        }

        return sb.toString();
    }

    static int find(int x, int[] parent) {
        if (parent[x] != x) parent[x] = find(parent[x], parent);
        return parent[x];
    }

    static void union(int a, int b, int[] parent) {
        a = find(a, parent);
        b = find(b, parent);
        if (a != b) parent[Math.max(a, b)] = Math.min(a, b);
    }
}

