import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        int M = Integer.parseInt(bf.readLine());

        List<Line> lines = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            lines.add(new Line(Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
        }

        System.out.println(solution(N, M, lines));
    }

    public static int solution(int N, int M, List<Line> lines) {
        int result = 0;
        int[] g = new int[N + 1];
        lines.sort(Comparator.comparingInt(Line::getV));

        int count = 0;

        for (Line l : lines) {
            int a = find(l.a, g);
            int b = find(l.b, g);

            if (a == b) continue;

            g[Math.max(a, b)] = Math.min(a, b);
            count++;
            result += l.v;

            if (count == N - 1) break;
        }

        return result;
    }

    public static int find(int n, int[] g) {
        if (g[n] == 0) return n;
        return g[n] = find(g[n], g);
    }

    public static class Line {
        int a;
        int b;
        int v;

        Line(int[] input) {
            a = input[0];
            b = input[1];
            v = input[2];
        }

        int getV() {
            return v;
        }
    }
}