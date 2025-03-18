import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int V = Integer.parseInt(input.nextToken());
        int E = Integer.parseInt(input.nextToken());

        PriorityQueue<Line> lines = new PriorityQueue<Line>(Comparator.comparingInt(Line::getV));

        for (int i = 0; i < E; i++) {
            input = new StringTokenizer(bf.readLine());
            lines.add(new Line(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken())));
        }

        System.out.println(solution(V, E, lines));
    }

    public static int solution(int V, int E, PriorityQueue<Line> lines) {
        int[] g = IntStream.rangeClosed(0, V).toArray();
        int result = 0;
        int count = 0;

        while (!lines.isEmpty()) {
            Line line = lines.remove();
            if (count == V - 1) break;

            int a = find(line.a, g);
            int b = find(line.b, g);

            if (a == b) continue;

            g[Math.max(a, b)] = Math.min(a, b);
            result += line.v;
            count++;
        }

        return result;
    }

    static int find(int n, int[] g) {
        if (g[n] == n) return n;
        return g[n] = find(g[n], g);
    }

    static class Line {
        int a;
        int b;
        int v;

        public Line(int a, int b, int v) {
            this.a = a;
            this.b = b;
            this.v = v;
        }

        public int getV() {
            return v;
        }
    }
}