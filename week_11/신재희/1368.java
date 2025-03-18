import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] cost = new int[N];

        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(bf.readLine());
        }

        List<Line> lines = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(input.nextToken());
                if (i >= j) continue;
                lines.add(new Line(i, j, n));
            }
        }

        System.out.println(solution(N, cost, lines));
    }

    public static int solution(int N, int[] cost, List<Line> lines) {
        int result = 0;
        int[] g = IntStream.range(0, N).toArray();
        lines.sort(Comparator.comparingInt(Line::getV));

        for (int i = 0; i < N; i++) {
            result += cost[i];
        }

        for (Line l : lines) {
            int a = find(l.a, g);
            int b = find(l.b, g);

            if (a == b) continue;

            int max = Math.max(cost[a], cost[b]);

            if (max < l.v) continue;

            cost[a] = cost[b] = Math.min(cost[a], cost[b]);
            g[Math.max(a, b)] = Math.min(a, b);

            result -= max;
            result += l.v;
        }

        return result;
    }

    public static int find(int n, int[] g) {
        if (g[n] == n) return n;
        return g[n] = find(g[n], g);
    }

    public static class Line {
        int a;
        int b;
        int v;

        Line(int a, int b, int v) {
            this.a = a;
            this.b = b;
            this.v = v;
        }

        int getV() {
            return v;
        }
    }
}