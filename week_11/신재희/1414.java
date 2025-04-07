import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var pq = new PriorityQueue<Line>(Comparator.comparingInt(Line::getLine));

        for (int i=0; i<N; i++) {
            var now = bf.readLine().toCharArray();

            for (int j=0; j<N; j++) {
                if (now[j]=='0') continue;
                pq.add(new Line(i, j, now[j]));
            }
        }

        System.out.println(solution(N, pq));
    }

    public static int solution(int N, PriorityQueue<Line> pq) {
        var g = IntStream.range(0, N).toArray();
        var result = 0;
        var cnt = 0;

        while (!pq.isEmpty()) {
            var now = pq.remove();

            var x = find(now.x, g);
            var y = find(now.y, g);

            if (x==y) {
                result += now.line;
            } else {
                g[Math.max(x, y)] = Math.min(x, y);
                cnt++;
            }
        }

        if (cnt==N-1) {
            return result;
        }
        return -1;
    }

    public static int find(int n, int[] g) {
        if (n!=g[n]) {
            g[n] = find(g[n], g);
        }
        return g[n];
    }

    static class Line {
        int x;
        int y;
        int line;

        public Line(int x, int y, char line) {
            this.x = x;
            this.y = y;
            if ('a' <= line && line <= 'z') {
                this.line = line - 'a' + 1;
            } else if ('A' <= line && line <= 'Z') {
                this.line = line - 'A' + 27;
            }
        }

        public int getLine() {
            return line;
        }
    }
}